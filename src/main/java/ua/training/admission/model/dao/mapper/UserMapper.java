package ua.training.admission.model.dao.mapper;

import ua.training.admission.model.entity.User;
import ua.training.admission.view.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * User Mapper
 *
 * @author Oleksii Morenets
 */
public class UserMapper extends ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = User.builder()
                .id(resultSet.getLong(SQL.USER_ID))
                .username(resultSet.getString(SQL.USER_USERNAME))
                .password(resultSet.getString(SQL.USER_PASSWORD))
                .email(resultSet.getString(SQL.USER_EMAIL))
                .firstName(resultSet.getString(SQL.USER_FIRST_NAME))
                .lastName(resultSet.getString(SQL.USER_LAST_NAME))
                .roles(new HashSet<>())
                .build();

        return makeUnique(user, user.getId());
    }
}
