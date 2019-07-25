package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.Speciality;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JdbcUserDao
 */
public class JdbcUserDao implements UserDao {

    private static final Logger LOG = Logger.getLogger(JdbcUserDao.class);
    private Connection connection;

    JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SQL.SELECT_USER_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                User user = getEntityFromResultSet(resultSet);
                result = Optional.of(user);
            }
        } catch (SQLException ex) {
            throw new AppException(Messages.SQL_ERROR, ex);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(User user) {
        try (PreparedStatement query = connection.prepareStatement(
                SQL.INSERT_INTO_USER, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, user.getUsername());
            query.setString(2, user.getPassword());
            query.setString(3, user.getEmail());
            query.setString(4, user.getFirstName());
            query.setString(5, user.getLastName());
            query.setString(6, User.Role.USER.name());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                user.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new AppException(Messages.SQL_ERROR, e);
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
    public Optional<User> findByUsername(String username) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_USER_BY_USERNAME)) {
            stmt.setString(1, username.toLowerCase());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                User user = getEntityFromResultSet(resultSet);
                result = Optional.of(user);
            }

        } catch (SQLException ex) {
            throw new AppException(Messages.SQL_ERROR, ex);
        }

        return result;
    }

    @Override
    public List<User> findAllByRole(User.Role role) {
        List<User> result = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_USERS_BY_ROLE)) {
            stmt.setString(1, role.name());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                final User user = getEntityFromResultSet(resultSet);
                final long specialityId = resultSet.getLong(SQL.USER_SPECIALITY_ID);
                if (specialityId > 0) {
                    user.setSpeciality(Speciality.builder()
                            .id(specialityId)
                            .name(resultSet.getString(SQL.SPECIALITY_NAME))
                            .build());
                }
                result.add(user);
            }

        } catch (SQLException e) {
            throw new AppException(Messages.SQL_ERROR, e);
        }

        return result;
    }

    private User getEntityFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong(SQL.USER_ID))
                .username(rs.getString(SQL.USER_USERNAME))
                .password(rs.getString(SQL.USER_PASSWORD))
                .email(rs.getString(SQL.USER_EMAIL))
                .firstName(rs.getString(SQL.USER_FIRST_NAME))
                .lastName(rs.getString(SQL.USER_LAST_NAME))
                .role(User.Role.valueOf(rs.getString(SQL.USER_ROLE)))
                .build();
    }
}
