package ua.training.admission.model.dao.jdbc;

import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.*;
import ua.training.admission.view.Config;
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

    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup(Config.JAVA_COMP_ENV_JDBC);
        } catch (NamingException e) {
            throw new AppException(Messages.NAMING_EXCEPTION, e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new AppException(Messages.SQL_ERROR, e);
        }
    }

    @Override
    public UserDao createUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcUserDao(sqlConnection);
    }

    @Override
    public SpecialityDao createSpecialityDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcSpecialityDao(sqlConnection);
    }

    @Override
    public SubjectDao createSubjectDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcSubjectDao(sqlConnection);
    }

    @Override
    public SubjectGradeDao createSubjectGradeDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcSubjectGradeDao(sqlConnection);
    }
}
