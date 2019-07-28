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

    private static final Logger log = Logger.getLogger(JdbcUserDao.class);
    private Connection connection;

    JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_USER_BY_ID)) {
            stmt.setString(1, String.valueOf(id));
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                User user = getEntityFromResultSet(resultSet);
                setSpeciality(user, resultSet);
                result = Optional.of(user);
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(User user) {
        try (PreparedStatement stmt = connection.prepareStatement(
                SQL.INSERT_INTO_USER, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, User.Role.USER.name());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            if (keys.next()) {
                user.setId(keys.getLong(1));
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL.UPDATE_USER)) {
            if (user.getSpeciality() == null) {
                stmt.setNull(1, Types.BIGINT);
            } else {
                Long specId = user.getSpeciality().getId();
                stmt.setLong(1, specId);
            }
            stmt.setLong(2, user.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
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

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }

    @Override
    public List<User> findAllByRole(User.Role role, int currentPage, int recordsPerPage) {
        List<User> result = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_USERS_BY_ROLE)) {
            stmt.setString(1, role.name());
            stmt.setInt(2, start);
            stmt.setInt(3, recordsPerPage);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                User user = getEntityFromResultSet(resultSet);
                setSpeciality(user, resultSet);
                result.add(user);
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }

    @Override
    public int getNumberOfRowsUsers() {
        int result = 0;

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(SQL.SELECT_COUNT_USERS);
            if (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
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

    private void setSpeciality(User user, ResultSet resultSet) throws SQLException {
        long specialityId = resultSet.getLong(SQL.USER_SPECIALITY_ID);
        if (!resultSet.wasNull()) {
            user.setSpeciality(Speciality.builder()
                    .id(specialityId)
                    .name(resultSet.getString(SQL.SPECIALITY_NAME))
                    .build());
        }
    }
}
