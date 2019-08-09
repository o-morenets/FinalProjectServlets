package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.RoleDao;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
    }

    @Override
    public List<Role> findAll() {
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
    }

    @Override
    public void create(Role role) {
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
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
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException(Messages.UNSUPPORTED_OPERATION_EXCEPTION);
    }
}
