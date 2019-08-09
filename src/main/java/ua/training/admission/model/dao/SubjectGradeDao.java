package ua.training.admission.model.dao;

import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * SubjectGrade DAO
 *
 * @author Oleksii Morenets
 */
public interface SubjectGradeDao extends GenericDao<SubjectGrade> {

    /**
     * Finds SubjectGrade entity for specified user ID and Subject ID
     *
     * @param userId    user ID
     * @param subjectId subject ID
     * @return optional SubjectGrade Entity
     */
    Optional<SubjectGrade> findByUserIdAndSubjectId(Long userId, Long subjectId);

    /**
     * Finds all SubjectGrades for specified user
     * @param user User entity
     * @return list of all SubjectGrade objects
     */
    List<SubjectGrade> findAllUserSubjectGradeByUser(User user);

    /**
     * Deletes SubjectGrade entity for specified user ID and subject ID
     * @param userId    user ID
     * @param subjectId subject ID
     */
    void deleteByUserIdAndSubjectId(Long userId, Long subjectId);
}
