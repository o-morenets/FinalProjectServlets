package ua.training.admission.model.dao.mapper;

import ua.training.admission.model.entity.Subject;
import ua.training.admission.view.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class SubjectMapper extends ObjectMapper<Subject> {

    @Override
    public Subject extractFromResultSet(ResultSet resultSet) throws SQLException {
        Subject subject = Subject.builder()
                .id(resultSet.getLong(SQL.SUBJECT_ID))
                .name(resultSet.getString(SQL.SUBJECT_NAME))
                .specialities(new HashSet<>())
                .build();
        subject = makeUnique(subject, subject.getId());

        return subject;
    }
}
