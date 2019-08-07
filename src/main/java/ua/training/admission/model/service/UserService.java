package ua.training.admission.model.service;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.controller.exception.NotUniqueUsernameException;
import ua.training.admission.model.dao.*;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.Speciality;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Messages;
import ua.training.admission.view.SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * User Service
 *
 * @author Oleksii Morenets
 */
public class UserService {

    /* Logger */
    private static final Logger log = Logger.getLogger(UserService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Lazy holder for service instance
     */
    private static class Holder {
        static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Returns logged user if authorization success
     *
     * @param username username
     * @param password password
     * @return optional - logged user
     */
    public Optional<User> login(String username, String password) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findByUsername(username)
                    .filter(user -> password.equals(user.getPassword()));
        }
    }

    /**
     * Finda a User by its id
     *
     * @param Id user id
     * @return optional User found by its id
     */
    public Optional<User> findById(Long Id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findById(Id);
        }
    }

    /**
     * Creates new User
     *
     * @param user User entity
     */
    public void create(User user) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            RoleDao roleDao = daoFactory.createRoleDao(connection);

            connection.beginTransaction();
            userDao.create(user);
            roleDao.createUserRole(user, Role.USER);
            connection.commit();

        } catch (AppException e) {
            int errorCode = 0;

            Throwable sqlEx = e.getCause();
            if (sqlEx instanceof SQLException) {
                SQLException sqlException = (SQLException) sqlEx;
                errorCode = sqlException.getErrorCode();
            }

            if (errorCode == SQL.SQL_CONSTRAINT_NOT_UNIQUE) {
                throw new NotUniqueUsernameException(Messages.USER_ALREADY_EXISTS);
            }

            throw e;
        }
    }

    /**
     * Finds all users by role
     *
     * @param role           user role
     * @param currentPage    current page - for pagination
     * @param recordsPerPage records per page - for pagination
     * @return list of users by specified role
     */
    public List<User> findAllByRole(Role role, int currentPage, int recordsPerPage) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findAllByRole(role, currentPage, recordsPerPage);
        }
    }

    /**
     * Returns number of users by role
     *
     * @param role user role
     * @return number of rows in user list
     */
    public int getNumberOfRowsByRole(Role role) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.getNumberOfRowsByRole(role);
        }
    }

    /**
     * Updates user speciality
     *
     * @param userId user id
     * @param specId speciality id
     */
    public void updateSpeciality(Long userId, Long specId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            userDao.findById(userId).ifPresent(user -> {
                SpecialityDao specialityDao = daoFactory.createSpecialityDao(connection);
                Optional<Speciality> speciality = specialityDao.findById(specId);
                speciality.ifPresent(user::setSpeciality);
                userDao.update(user);
            });
        }
    }
}
