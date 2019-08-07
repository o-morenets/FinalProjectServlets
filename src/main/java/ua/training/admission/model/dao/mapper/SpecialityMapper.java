package ua.training.admission.model.dao.mapper;

import ua.training.admission.model.entity.Speciality;
import ua.training.admission.view.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class SpecialityMapper extends ObjectMapper<Speciality> {

    @Override
    public Speciality extractFromResultSet(ResultSet resultSet) throws SQLException {
        Speciality speciality = null;
        Long specialityId = resultSet.getLong(SQL.SPECIALITY_ID);
        if (!resultSet.wasNull()) {
            speciality = Speciality.builder()
                    .id(specialityId)
                    .name(resultSet.getString(SQL.SPECIALITY_NAME))
                    .subjects(new HashSet<>())
                    .build();
            speciality = makeUnique(speciality, speciality.getId());
        }

        return speciality;
    }
}
