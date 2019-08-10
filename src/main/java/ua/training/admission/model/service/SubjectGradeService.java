package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.*;
import ua.training.admission.model.entity.Message;
import ua.training.admission.model.entity.Subject;
import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Constants;
import ua.training.admission.view.Messages;

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
     * @param userId user id
     * @param form   form parameters from http request
     */
    public void updateGrades(Long userId, Map<String, String> form) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            userDao.findById(userId).ifPresent(user -> {
                Map<Boolean, List<SubjectGrade>> subjectGradeMap = partitionSubjectGrades(user, form);
                List<SubjectGrade> subjectGradesToUpdate = subjectGradeMap.get(Boolean.TRUE);
                List<SubjectGrade> subjectGradesToDelete = subjectGradeMap.get(Boolean.FALSE);

                connection.beginTransaction();
                updateAndDelete(subjectGradesToUpdate, subjectGradesToDelete);
                updateUserMessage(user, subjectGradesToUpdate, subjectGradesToDelete);
                connection.commit();
            });
        }
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
                                .id(Long.parseLong(entry.getKey().replaceAll("\\D+", "")))
                                .build())
                        .grade(entry.getValue().isEmpty() ? null : Integer.parseInt(entry.getValue()))
                        .build())
                .collect(partitioningBy(subjectGrade -> subjectGrade.getGrade() != null));
    }

    /**
     * Updates and deletes user-grade records
     *
     * @param subjectGradesToUpdate subject-grade list for update
     * @param subjectGradesToDelete subject-grade list for delete
     */
    private void updateAndDelete(List<SubjectGrade> subjectGradesToUpdate, List<SubjectGrade> subjectGradesToDelete) {
        subjectGradesToUpdate.forEach(this::save);
        subjectGradesToDelete.forEach(this::delete);
    }

    /**
     * Creates or updates (if already exists) subjectGrade Entity
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
            subjectGradeDao.deleteByUserIdAndSubjectId(
                    subjectGrade.getUser().getId(),
                    subjectGrade.getSubject().getId()
            );
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
                .orElseThrow(() -> new RuntimeException(Messages.COUNT_AVERAGE_GRADE_EXCEPTION));
    }
}
