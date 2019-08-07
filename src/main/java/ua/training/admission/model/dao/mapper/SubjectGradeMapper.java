package ua.training.admission.model.dao.mapper;

import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.view.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SubjectGrade Mapper
 *
 * @author Oleksii Morenets
 */
public class SubjectGradeMapper extends ObjectMapper<SubjectGrade> {

    @Override
    public SubjectGrade extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer grade = resultSet.getInt(SQL.SUBJECT_GRADE_GRADE);
        if (resultSet.wasNull()) {
            grade = null;
        }

        return SubjectGrade.builder()
                .grade(grade)
                .build();
    }
}
