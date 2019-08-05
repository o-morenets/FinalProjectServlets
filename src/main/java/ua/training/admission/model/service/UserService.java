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
 * UserService
 */
public class UserService {

    /* Logger */
    private static final Logger log = Logger.getLogger(UserService.class);

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
                    .filter(user -> password.equals(user.getPassword()));
        }
    }

    public Optional<User> findById(Long Id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findById(Id);
        }
    }

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

    public List<User> findAllByRole(Role role, int currentPage, int recordsPerPage) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findAllByRole(role, currentPage, recordsPerPage);
        }
    }

    public int getNumberOfRowsByRole(Role role) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.getNumberOfRowsByRole(role);
        }
    }

    public void updateSpeciality(Long userId, Long specId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            Optional<User> user = userDao.findById(userId);
            user.ifPresent(usr -> {
                SpecialityDao specialityDao = daoFactory.createSpecialityDao(connection);
                Optional<Speciality> speciality = specialityDao.findById(specId);
                speciality.ifPresent(usr::setSpeciality);
                userDao.update(usr);
            });
        }
    }
}
