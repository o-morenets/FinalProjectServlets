package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.model.dao.*;
import ua.training.admission.model.entity.Message;
import ua.training.admission.model.entity.Subject;
import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.stream.Collectors.partitioningBy;

public class SubjectGradeService {

    /* Logger */
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
                Map<String, String> form = extractFormParameters(request);

                connection.beginTransaction();
                updateAndDelete(usr, partitionSubjectGrades(usr, form));
                connection.commit();
            });
        }
    }

    private Map<String, String> extractFormParameters(HttpServletRequest request) {
        Map<String, String> form = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String elementName = parameterNames.nextElement();

            if (elementName.startsWith(Constants.PREFIX_SUBJECT)) {
                String paramGrade = request.getParameter(elementName);
                form.put(elementName, paramGrade);
            }
        }

        return form;
    }

    private void updateAndDelete(User user, Map<Boolean, List<SubjectGrade>> subjectGradeMap) {
        List<SubjectGrade> subjectGradesToUpdate = subjectGradeMap.get(true);
        subjectGradesToUpdate.forEach(this::save);

        List<SubjectGrade> subjectGradesToDelete = subjectGradeMap.get(false);
        subjectGradesToDelete.forEach(this::delete);

        updateUserMessage(user, subjectGradesToUpdate, subjectGradesToDelete);
    }

    private Map<Boolean, List<SubjectGrade>> partitionSubjectGrades(User user, Map<String, String> form) {
        return form.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("subject_"))
                .map(entry -> SubjectGrade.builder()
                        .user(User.builder()
                                .id(user.getId())
                                .build())
                        .subject(Subject.builder()
                                .id(Long.valueOf(entry.getKey().replaceAll("\\D+", "")))
                                .build())
                        .grade(entry.getValue().isEmpty() ? null : Integer.parseInt(entry.getValue()))
                        .build())
                .collect(partitioningBy(subjectGrade -> subjectGrade.getGrade() != null));
    }

    private void save(SubjectGrade subjectGrade) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);

            if (subjectGradeDao.findByUserIdAndSubjectId(
                    subjectGrade.getUser().getId(), subjectGrade.getSubject().getId()).isPresent()) {
                subjectGradeDao.update(subjectGrade);
            } else {
                subjectGradeDao.create(subjectGrade);
            }
        }
    }

    private void delete(SubjectGrade subjectGrade) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);
            subjectGradeDao.deleteByUserIdAndSubjectId(subjectGrade.getUser().getId(), subjectGrade.getSubject().getId());
        }
    }

    private void updateUserMessage(User user,
                                   List<SubjectGrade> subjectGradesToUpdate,
                                   List<SubjectGrade> subjectGradesToDelete
    ) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            MessageDao messageDao = daoFactory.createMessageDao(connection);

            Message message = user.getMessage();
            boolean isMessageSet = message != null;
            if (!isMessageSet) {
                message = Message.builder()
                        .user(user)
                        .build();
            }

            boolean isAllGradesProvided = subjectGradesToDelete.isEmpty();
            if (isAllGradesProvided) {
                message.setAverageGrade(countAverageGrade(subjectGradesToUpdate));
                if (isMessageSet) {
                    messageDao.update(message);
                } else {
                    messageDao.create(message);
                }
            } else {
                messageDao.delete(user.getId());
            }
        }
    }

    private double countAverageGrade(List<SubjectGrade> subjectGradesFinal) {
        return subjectGradesFinal.stream()
                .mapToDouble(SubjectGrade::getGrade)
                .average()
                .orElse(-1); // TODO maybe orElseThrow and then catch ???
    }
}
