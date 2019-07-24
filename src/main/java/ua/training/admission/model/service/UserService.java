package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.controller.exception.NotUniqueUsernameException;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * UserService
 */
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {

        static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<User> login(String username, String password) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findByUsername(username)
                    .filter(staff -> password.equals(staff.getPassword()));
        }
    }

    public Optional<User> findUserById(int Id) {
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

            if (errorCode == SQL.SQL_CONSTRAINT_NOT_UNIQUE) {
                LOG.warn(Messages.USER_ALREADY_EXISTS);

                throw new NotUniqueUsernameException(Messages.USER_ALREADY_EXISTS);
            }

            throw ex;
        }
    }

    public List<User> findAllUsersByRole(User.Role role) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            return userDao.findAllByRole(role);
        }
    }
}
