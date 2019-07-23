package ua.training.admission.model.service;

import java.util.Optional;

import ua.training.admission.controller.exception.NotUniqueUsernameException;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.User;

/**
 * UserService
 */
public class UserService {

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
        // TODO
        throw new NotUniqueUsernameException("Not unique username");
    }
}
