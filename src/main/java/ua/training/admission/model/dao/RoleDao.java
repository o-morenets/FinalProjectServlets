package ua.training.admission.model.dao;

import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;

/**
 * Role DAO
 *
 * @author Oleksii Morenets
 */
public interface RoleDao extends GenericDao<Role> {

    void createUserRole(User user, Role role);

}
