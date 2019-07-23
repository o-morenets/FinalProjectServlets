package ua.training.admission.model.dao;

import ua.training.admission.model.entity.User;

import java.util.Optional;

/**
 * interface UserDao
 */
public interface UserDao extends GenericDao<User> {

	Optional<User> getUserByUsername(String username);

}
