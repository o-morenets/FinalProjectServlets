package ua.training.admission.security;

import ua.training.admission.model.entity.User;
import ua.training.admission.view.Paths;

import java.util.*;

public class SecurityConfig {

    private static final Map<User.Role, List<String>> securedPages = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        securedPages.put(User.Role.GUEST, Arrays.asList(
                Paths.HOME,
                Paths.LOGIN,
                Paths.SIGNUP
        ));
        securedPages.put(User.Role.USER, Arrays.asList(
                Paths.USERS_SPECIALITY,
                Paths.USERS_,
                Paths.USERS_GRADES // FIXME
//                Paths.USERS_UPDATE_SPEC
        ));

        securedPages.put(User.Role.ADMIN, Arrays.asList(
                Paths.USERS,
                Paths.USERS_GRADES // FIXME
//                Paths.USERS_UPDATE_GRADES
        ));
    }

    public static Set<User.Role> getAllAppRoles() {
        return securedPages.keySet();
    }

    public static List<String> getUrlPatternsForRole(User.Role role) {
        return securedPages.get(role);
    }
}
