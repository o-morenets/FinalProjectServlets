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

	Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role, int currentPage, int recordsPerPage);

    int getNumberOfRowsByRole(Role role);

}
