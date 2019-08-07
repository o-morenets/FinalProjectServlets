package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.dao.mapper.MessageMapper;
import ua.training.admission.model.dao.mapper.RoleMapper;
import ua.training.admission.model.dao.mapper.SpecialityMapper;
import ua.training.admission.model.dao.mapper.UserMapper;
import ua.training.admission.model.entity.Message;
import ua.training.admission.model.entity.Role;
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
 *
 * @author Oleksii Morenets
 */
public class JdbcUserDao implements UserDao {

    /* Logger */
    private static final Logger log = Logger.getLogger(JdbcUserDao.class);

    private Connection connection;

    JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> result;

        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.SELECT_USER_BY_ID))) {
            stmt.setString(1, String.valueOf(id));
            ResultSet resultSet = stmt.executeQuery();

            User user = null;
            UserMapper userMapper = new UserMapper();
            MessageMapper messageMapper = new MessageMapper();
            SpecialityMapper specialityMapper = new SpecialityMapper();

            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
                Message message = messageMapper.extractFromResultSet(resultSet);
                if (message != null) {
                    message.setUser(user);
                }
                user.setMessage(message);
                user.setSpeciality(specialityMapper.extractFromResultSet(resultSet));
            }

            result = Optional.ofNullable(user);

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
                SQL.getSqlElement(SQL.INSERT_INTO_USER), Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatement(stmt, user);
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

    private void setPreparedStatement(PreparedStatement stmt, User user) throws SQLException {
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getFirstName());
        stmt.setString(5, user.getLastName());

        Speciality speciality = user.getSpeciality();
        if (speciality == null) {
            stmt.setNull(6, Types.BIGINT);
        } else {
            Long specId = speciality.getId();
            stmt.setLong(6, specId);
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.UPDATE_USER))) {
            setPreparedStatement(stmt, user);
            stmt.setLong(7, user.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> result;

        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.SELECT_USER_BY_USERNAME))) {
            stmt.setString(1, username.toLowerCase());
            ResultSet resultSet = stmt.executeQuery();

            User user = null;
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();
            MessageMapper messageMapper = new MessageMapper();

            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
                user.getRoles().add(roleMapper.extractFromResultSet(resultSet));
                Message message = messageMapper.extractFromResultSet(resultSet);
                if (message != null) {
                    message.setUser(user);
                }
                user.setMessage(message);
            }
            result = Optional.ofNullable(user);

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }

    @Override
    public List<User> findAllByRole(Role role, int currentPage, int recordsPerPage) {
        List<User> result = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.SELECT_USERS_BY_ROLE))) {
            stmt.setString(1, role.name());
            stmt.setInt(2, start);
            stmt.setInt(3, recordsPerPage);
            ResultSet resultSet = stmt.executeQuery();

            UserMapper userMapper = new UserMapper();
            SpecialityMapper specialityMapper = new SpecialityMapper();
            MessageMapper messageMapper = new MessageMapper();

            while (resultSet.next()) {
                User user = userMapper.extractFromResultSet(resultSet);
                Message message = messageMapper.extractFromResultSet(resultSet);
                if (message != null) {
                    message.setUser(user);
                }
                user.setMessage(message);
                user.setSpeciality(specialityMapper.extractFromResultSet(resultSet));
                result.add(user);
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }

    @Override
    public int getNumberOfRowsByRole(Role role) {
        int result = 0;

        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.SELECT_COUNT_USERS_BY_ROLE))) {
            stmt.setString(1, role.name());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(SQL.COUNT_ALL);
            }

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }

        return result;
    }
}
