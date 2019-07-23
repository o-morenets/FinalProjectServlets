package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.controller.exception.NotUniqueUsernameException;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * UserService
 */
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);
    private static final int SQL_CONSTRAINT_NOT_UNIQUE = 1062;

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public Optional<User> login(String username, String password) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.getUserByUsername(username)
                    .filter(staff -> password.equals(staff.getPassword()));
        }
    }

    public Optional<User> getUserById(int Id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findById(Id);
        }
    }

    public void createUser(User user) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            userDao.create(user);
        } catch (AppException ex) {
            int errorCode = 0;

            final Throwable sqlEx = ex.getCause();
            if (sqlEx instanceof SQLException) {
                SQLException sqlException = (SQLException) sqlEx;
                errorCode = sqlException.getErrorCode();
            }

            if (errorCode == SQL_CONSTRAINT_NOT_UNIQUE) {
                LOG.warn("User already exists");

                throw new NotUniqueUsernameException("User already exists");
            }

            throw ex;
        }
    }
}
