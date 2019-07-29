package ua.training.admission.security;

import ua.training.admission.model.entity.User;
import ua.training.admission.view.Paths;

import java.util.*;

class SecurityConfig {

    private static final Map<User.Role, List<String>> securedPages = new HashMap<User.Role, List<String>>() {
        {
            put(User.Role.ADMIN, Arrays.asList(
                    Paths.USERS,
                    Paths.USER_GRADES
            ));

            put(User.Role.USER, Arrays.asList(
                    Paths.USER_PROFILE,
                    Paths.USER_SPECIALITY,
                    Paths.USER_GRADES
            ));
        }
    };

    static Map<User.Role, List<String>> getAllAppRoles() {
        return securedPages;
    }
}
