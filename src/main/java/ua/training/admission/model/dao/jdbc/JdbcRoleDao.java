package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.RoleDao;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.dao.mapper.RoleMapper;
import ua.training.admission.model.dao.mapper.SpecialityMapper;
import ua.training.admission.model.dao.mapper.UserMapper;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JdbcRoleDao
 *
 * @author Oleksii Morenets
 */
public class JdbcRoleDao implements RoleDao {

    /* Logger */
    private static final Logger log = Logger.getLogger(JdbcRoleDao.class);

    private Connection connection;

    JdbcRoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Role> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Role> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Role role) {

    }

    @Override
    public void createUserRole(User user, Role role) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.INSERT_INTO_USER_ROLES))) {
            stmt.setLong(1, user.getId());
            stmt.setString(2, role.name());
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(Role role) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
