package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.SubjectGradeDao;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.Subject;
import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.TextConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

public class SubjectGradeService {

    private static final Logger log = Logger.getLogger(SubjectGradeService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final SubjectGradeService INSTANCE = new SubjectGradeService();
    }

    public static SubjectGradeService getInstance() {
        return SubjectGradeService.Holder.INSTANCE;
    }

    public List<SubjectGrade> getUserSubjectGradeList(User user) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);

            return subjectGradeDao.findAllUserSubjectGradeByUser(user);
        }
    }

    public void updateGrades(Long userId, HttpServletRequest request) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            Optional<User> user = userDao.findById(userId);
            user.ifPresent(usr -> {
                SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);
                Enumeration<String> parameterNames = request.getParameterNames();

                connection.beginTransaction();
                subjectGradeDao.deleteByUserId(usr.getId());

                while (parameterNames.hasMoreElements()) {
                    String elementName = parameterNames.nextElement();

                    if (elementName.startsWith(TextConstants.PREFIX_SUBJECT)) {
                        try {
                            long subjectId = Long.parseLong(elementName.replaceAll("\\D+", ""));
                            try {
                                int grade = Integer.parseInt(request.getParameter(elementName));
                                subjectGradeDao.create(SubjectGrade.builder()
                                        .user(User.builder()
                                                .id(usr.getId())
                                                .build())
                                        .subject(Subject.builder()
                                                .id(subjectId)
                                                .build())
                                        .grade(grade)
                                        .build());

                            } catch (NumberFormatException ignored) {
                            }

                        } catch (NumberFormatException ignored) {
                        }
                    }
                }
                connection.commit();
            });
        }
    }
}
