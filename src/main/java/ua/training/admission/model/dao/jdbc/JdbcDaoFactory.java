package ua.training.admission.model.dao.jdbc;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.*;
import ua.training.admission.config.AppConfig;
import ua.training.admission.view.Messages;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * JdbcDaoConnection
 */
public class JdbcDaoFactory extends DaoFactory {

    /* Logger */
    private static final Logger log = Logger.getLogger(JdbcDaoFactory.class);

    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup(AppConfig.JAVA_COMP_ENV_JDBC);
        } catch (NamingException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.NAMING_EXCEPTION, e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            log.error(Messages.SQL_EXCEPTION, e);
            throw new AppException(Messages.SQL_EXCEPTION, e);
        }
    }

    @Override
    public UserDao createUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();

        return new JdbcUserDao(sqlConnection);
    }

    @Override
    public RoleDao createRoleDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();

        return new JdbcRoleDao(sqlConnection);
    }

    @Override
    public SpecialityDao createSpecialityDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();

        return new JdbcSpecialityDao(sqlConnection);
    }

    @Override
    public SubjectGradeDao createSubjectGradeDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();

        return new JdbcSubjectGradeDao(sqlConnection);
    }
}
