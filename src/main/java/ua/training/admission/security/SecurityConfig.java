package ua.training.admission.security;

import ua.training.admission.model.entity.Role;
import ua.training.admission.view.Paths;

import java.util.*;

/**
 * Config for application security
 *
 * @author Oleksii Morenets
 */
class SecurityConfig {

    /**
     * Storage for Secure Paths
     */
    private static final Map<Role, List<String>> securedPages = new HashMap<Role, List<String>>() {
        {
            put(Role.ADMIN, Arrays.asList(
                    Paths.USERS,
                    Paths.USER_GRADES
            ));

            put(Role.USER, Arrays.asList(
                    Paths.USER_PROFILE,
                    Paths.USER_SPECIALITY,
                    Paths.USER_GRADES
            ));
        }
    };

    static Map<Role, List<String>> getAllAppRoles() {
        return securedPages;
    }
}
