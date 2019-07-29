package ua.training.admission.model.dao;

import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface SubjectGradeDao extends GenericDao<SubjectGrade> {

    Optional<SubjectGrade> findByUserIdAndSubjectId(Long userId, Long subjectId);

    List<SubjectGrade> findAllUserSubjectGradeByUser(User user);

    void deleteByUserIdAndSubjectId(Long userId, Long subjectId);

}
