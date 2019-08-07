package ua.training.admission.model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.view.Messages;

/**
 * JdbcDaoConnection
 *
 * @author Oleksii Morenets
 */
public class JdbcDaoConnection implements DaoConnection {

    /* Logger */
    private static final Logger log = Logger.getLogger(JdbcDaoConnection.class);

    private Connection connection;
    private boolean inTransaction = false;

    JdbcDaoConnection(Connection connection) {
        super();
        this.connection = connection;
    }

    Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        if (inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION_CONNECTION_CLOSE, e);
            throw new AppException(Messages.SQL_EXCEPTION_CONNECTION_CLOSE, e);
        }
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION_TRANSACTION_BEGIN, e);
            throw new AppException(Messages.SQL_EXCEPTION_TRANSACTION_BEGIN, e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION_TRANSACTION_COMMIT, e);
            throw new AppException(Messages.SQL_EXCEPTION_TRANSACTION_COMMIT, e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION_TRANSACTION_ROLLBACK, e);
            throw new AppException(Messages.SQL_EXCEPTION_TRANSACTION_ROLLBACK, e);
        }
    }
}
