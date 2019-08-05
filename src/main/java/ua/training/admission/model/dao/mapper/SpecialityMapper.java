package ua.training.admission.model.dao.mapper;

import ua.training.admission.model.entity.Speciality;
import ua.training.admission.view.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityMapper extends ObjectMapper<Speciality> {

    @Override
    public Speciality extractFromResultSet(ResultSet resultSet) throws SQLException {
        Speciality speciality = null;

        Long specialityId = resultSet.getLong(SQL.USER_SPECIALITY_ID);
        if (!resultSet.wasNull()) {
            speciality = Speciality.builder()
                    .id(specialityId)
                    .name(resultSet.getString(SQL.SPECIALITY_NAME))
                    .build();
            speciality = makeUnique(speciality, speciality.getId());
        }

        return speciality;
    }
}
