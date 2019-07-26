package ua.training.admission.model.dao;

import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;

import java.util.List;

public interface SubjectGradeDao extends GenericDao<SubjectGrade> {

    List<SubjectGrade> findAllUserSubjectGradeByUser(User user);

}
