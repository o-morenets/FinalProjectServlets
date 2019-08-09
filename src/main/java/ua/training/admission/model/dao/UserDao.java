package ua.training.admission.model.dao;

import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * User DAO
 *
 * @author Oleksii Morenets
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Finds user by its username
     *
     * @param username user's username
     * @return optional user with given username
     */
	Optional<User> findByUsername(String username);

    /**
     * Finds all users by specified role
     *
     * @param role           user role
     * @param currentPage    current page for pageable list
     * @param recordsPerPage records per page for pageable list
     * @return all users with specified role
     */
    List<User> findAllByRole(Role role, int currentPage, int recordsPerPage);

    /**
     * Fetches number of rows for all users with specified role
     *
     * @param role user role
     * @return number of all users with specified role
     */
    int getNumberOfRowsByRole(Role role);
}
