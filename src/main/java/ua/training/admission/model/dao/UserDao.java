package ua.training.admission.model.dao;

import ua.training.admission.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * interface UserDao
 */
public interface UserDao extends GenericDao<User> {

	Optional<User> findByUsername(String username);

    List<User> findAllByRole(User.Role role, int currentPage, int recordsPerPage);

    int getNumberOfRowsUsers();

}
