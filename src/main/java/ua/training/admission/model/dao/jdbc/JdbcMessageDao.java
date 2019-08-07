package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.MessageDao;
import ua.training.admission.model.entity.Message;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * JdbcMessageDao
 *
 * @author Oleksii Morenets
 */
public class JdbcMessageDao implements MessageDao {

    /* Logger */
    private static final Logger log = Logger.getLogger(JdbcMessageDao.class);

    private Connection connection;

    JdbcMessageDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public void create(Message message) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.INSERT_INTO_MESSAGE))) {
            stmt.setDouble(1, message.getAverageGrade());
            stmt.setString(2, message.getMessage());
            stmt.setBoolean(3, message.isMessageRead());
            stmt.setLong(4, message.getUser().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(Message message) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.UPDATE_MESSAGE))) {
            stmt.setDouble(1, message.getAverageGrade());
            stmt.setString(2, message.getMessage());
            stmt.setBoolean(3, message.isMessageRead());
            stmt.setLong(4, message.getUser().getId());
            stmt.setLong(5, message.getUser().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL.getSqlElement(SQL.DELETE_MESSAGE))) {
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }
}
