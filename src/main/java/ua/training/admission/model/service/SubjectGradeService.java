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

/**
 * SubjectGrade Service
 *
 * @author Oleksii Morenets
 */
public class SubjectGradeService {

    /* Logger */
    private static final Logger log = Logger.getLogger(SubjectGradeService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Lazy holder for service instance
     */
    private static class Holder {
        static final SubjectGradeService INSTANCE = new SubjectGradeService();
    }

    public static SubjectGradeService getInstance() {
        return SubjectGradeService.Holder.INSTANCE;
    }

    /**
     * Returns SubjectGrade list for specified user
     *
     * @param user User entity
     * @return SubjectGrade list
     */
    public List<SubjectGrade> getUserSubjectGradeList(User user) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);

            return subjectGradeDao.findAllUserSubjectGradeByUser(user);
        }
    }

    /**
     * Updates user grades
     *
     * @param userId  user id
     * @param request http request
     */
    public void updateGrades(Long userId, HttpServletRequest request) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            userDao.findById(userId).ifPresent(user -> {
                Map<String, String> form = extractFormParameters(request);

                connection.beginTransaction();
                Map<Boolean, List<SubjectGrade>> subjectGradeMap = partitionSubjectGrades(user, form);
                updateAndDelete(user, subjectGradeMap);
                List<SubjectGrade> subjectGradesToUpdate = subjectGradeMap.get(true);
                List<SubjectGrade> subjectGradesToDelete = subjectGradeMap.get(false);
                updateUserMessage(user, subjectGradesToUpdate, subjectGradesToDelete);
                connection.commit();
            });
        }
    }

    /**
     * Helper method
     * Extracts form parameters from http request
     *
     * @param request http request
     * @return Map of subject-grade parameters
     */
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

    /**
     * Updates and deletes user-grade records
     *
     * @param user            User entity
     * @param subjectGradeMap subject-grade map
     */
    private void updateAndDelete(User user, Map<Boolean, List<SubjectGrade>> subjectGradeMap) {
        List<SubjectGrade> subjectGradesToUpdate = subjectGradeMap.get(true);
        subjectGradesToUpdate.forEach(this::save);

        List<SubjectGrade> subjectGradesToDelete = subjectGradeMap.get(false);
        subjectGradesToDelete.forEach(this::delete);
    }

    /**
     * Partition SubjectGrade List in two parts - List for delete and List for create/update
     *
     * @param user User entity
     * @param form subject-grade form parameters
     * @return two lists of SubjectGrade entity
     */
    private Map<Boolean, List<SubjectGrade>> partitionSubjectGrades(User user, Map<String, String> form) {
        return form.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(Constants.PREFIX_SUBJECT))
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

    /**
     * Creates or updates subjectGrade Entity
     *
     * @param subjectGrade SubjectGrade Entity
     */
    private void save(SubjectGrade subjectGrade) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);

            if (subjectGradeDao.findByUserIdAndSubjectId(
                    subjectGrade.getUser().getId(), subjectGrade.getSubject().getId()
            ).isPresent()) {

                subjectGradeDao.update(subjectGrade);
            } else {
                subjectGradeDao.create(subjectGrade);
            }
        }
    }

    /**
     * Deletes SubjectGrade Entity
     *
     * @param subjectGrade SubjectGrade Entity
     */
    private void delete(SubjectGrade subjectGrade) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);
            subjectGradeDao.deleteByUserIdAndSubjectId(subjectGrade.getUser().getId(), subjectGrade.getSubject().getId());
        }
    }

    /**
     * Updates user message if ALL grades are present
     *
     * @param user                  User entity
     * @param subjectGradesToUpdate List for create/update SubjectGrade
     * @param subjectGradesToDelete list for delete SubjectGrade
     */
    private void updateUserMessage(User user,
                                   List<SubjectGrade> subjectGradesToUpdate,
                                   List<SubjectGrade> subjectGradesToDelete) {

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

    /**
     * Counts average grade for ALL user subjects
     *
     * @param subjectGradesFinal filled list of SubjectGrades
     * @return an average value for all user subjects
     */
    private double countAverageGrade(List<SubjectGrade> subjectGradesFinal) {
        return subjectGradesFinal.stream()
                .mapToDouble(SubjectGrade::getGrade)
                .average()
                .orElse(-1); // TODO maybe orElseThrow and then catch ???
    }
}
