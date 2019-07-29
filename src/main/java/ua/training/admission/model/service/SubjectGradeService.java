package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.SubjectGradeDao;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.Subject;
import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Messages;
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
                while (parameterNames.hasMoreElements()) {
                    String elementName = parameterNames.nextElement();

                    if (elementName.startsWith(TextConstants.PREFIX_SUBJECT)) {
                        try {
                            long subjectId = Long.parseLong(elementName.replaceAll("\\D+", ""));
                            try {
                                int grade = Integer.parseInt(request.getParameter(elementName));
                                saveOrUpdate(usr.getId(), subjectId, grade);

                            } catch (NumberFormatException e) {
                                log.error(Messages.NUMBER_FORMAT_EXCEPTION, e);
                                subjectGradeDao.deleteByUserIdAndSubjectId(usr.getId(), subjectId);
                            }

                        } catch (NumberFormatException e) {
                            log.error(Messages.NUMBER_FORMAT_EXCEPTION, e);
                        }
                    }
                }
                connection.commit();
            });
        }
    }

    private void saveOrUpdate(Long userId, Long subjectId, int grade) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);

            SubjectGrade subjectGrade = SubjectGrade.builder()
                    .user(User.builder()
                            .id(userId)
                            .build())
                    .subject(Subject.builder()
                            .id(subjectId)
                            .build())
                    .grade(grade)
                    .build();

            if (subjectGradeDao.findByUserIdAndSubjectId(userId, subjectId).isPresent()) {
                subjectGradeDao.update(subjectGrade);
            } else {
                subjectGradeDao.create(subjectGrade);
            }
        }
    }
}
