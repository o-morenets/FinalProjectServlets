package ua.training.admission.model.dao.jdbc;

import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Errors;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * JdbcUserDao
 */
public class JdbcUserDao implements UserDao {

    /* SQL */
    private static final String SELECT_STAFF_BY_LOGIN = "SELECT * FROM usr WHERE lower(username) = ?";
    private static final String SELECT_STAFF_BY_ID = "SELECT * FROM usr WHERE id = ?";
    private static final String INSERT_INTO_USR = "INSERT INTO usr" +
            " (username, password, email, first_name, last_name, role)" +
            " VALUES (?, ?, ?, ?, ?, ?)";

    /* Fields */
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String LAST_NAME = "last_name";
    private static final String FIRST_NAME = "first_name";
    private static final String ROLE = "role";

    private Connection connection;

    JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SELECT_STAFF_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                User user = getEntityFromResultSet(resultSet);
                result = Optional.of(user);
            }
        } catch (SQLException ex) {
            throw new AppException(Errors.SQL_ERROR, ex);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(User user) {
        try (PreparedStatement query = connection.prepareStatement(INSERT_INTO_USR, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, user.getUsername());
            query.setString(2, user.getPassword());
            query.setString(3, user.getEmail());
            query.setString(4, user.getFirstName());
            query.setString(5, user.getLastName());
            query.setString(6, user.getRole().name());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SELECT_STAFF_BY_LOGIN)) {
            query.setString(1, username.toLowerCase());
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                User user = getEntityFromResultSet(resultSet);
                result = Optional.of(user);
            }
        } catch (SQLException ex) {
            throw new AppException(Errors.SQL_ERROR, ex);
        }
        return result;
    }

    private User getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .setId(rs.getInt(ID))
                .username(rs.getString(USERNAME))
                .password(rs.getString(PASSWORD))
                .email(rs.getString(EMAIL))
                .firstName(rs.getString(FIRST_NAME))
                .lastName(rs.getString(LAST_NAME))
                .role(User.Role.valueOf(rs.getString(ROLE)))
                .build();
    }
}
