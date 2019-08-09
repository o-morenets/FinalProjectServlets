package ua.training.admission.model.dao;

import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;

/**
 * Role DAO
 *
 * @author Oleksii Morenets
 */
public interface RoleDao extends GenericDao<Role> {

    /**
     * Creates role for specified user
     *
     * @param user User entity
     * @param role user role
     */
    void createUserRole(User user, Role role);
}
