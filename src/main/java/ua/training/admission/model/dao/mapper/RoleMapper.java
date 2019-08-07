package ua.training.admission.model.dao.mapper;

import ua.training.admission.model.entity.Role;
import ua.training.admission.view.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Role Mapper
 *
 * @author Oleksii Morenets
 */
public class RoleMapper extends ObjectMapper<Role> {

    @Override
    public Role extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Role.valueOf(resultSet.getString(SQL.USER_ROLE_ROLE_NAME));
    }
}
