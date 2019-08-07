package ua.training.admission.security;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Security Utils
 *
 * @author Oleksii Morenets
 */
public class SecurityUtils {

    /* Variables for redirect after login success */
    private static int REDIRECT_ID = 0;
    private static final Map<Integer, String> id_uri_map = new HashMap<>();
    private static final Map<String, Integer> uri_id_map = new HashMap<>();

    /* Logger */
    private static final Logger log = Logger.getLogger(SecurityUtils.class);

    /**
     * Store user info in Session
     * On the JSP can access via ${Attributes.PRINCIPAL}
     *
     * @param session user session
     * @param user    User entity
     */
    public static void storeLoggedUser(HttpSession session, User user) {
        session.setAttribute(Attributes.PRINCIPAL, user);
    }

    /**
     * Get user info from Session
     *
     * @param session user session
     * @return User Entity from user session
     */
    public static User getLoggedUser(HttpSession session) {
        return (User) session.getAttribute(Attributes.PRINCIPAL);
    }

    /**
     * Store redirect link after login success
     *
     * @param session    user session
     * @param requestUri request URI
     * @return redirect ID
     */
    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }

        return id;
    }

    /**
     * Returns redirect link after login success
     *
     * @param redirectId redirect ID
     * @return redirect link
     */
    public static String getRedirectAfterLoginUrl(int redirectId) {
        return id_uri_map.get(redirectId);
    }

    /**
     * Check whether this 'request' is required to login or not
     *
     * @param request http request
     * @return true if this 'request' is required to login, false otherwise
     */
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);

        return SecurityConfig.getAllAppRoles().values().stream()
                .flatMap(Collection::stream)
                .distinct()
                .anyMatch(urlPattern::equals);
    }

    /**
     * Check if this 'request' has a 'valid role'?
     *
     * @param request http request
     * @return true if this 'request' has a 'valid role, false otherwise
     */
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);

        Set<Role> allAppRoles = SecurityConfig.getAllAppRoles().keySet();

        for (Role role : allAppRoles) {
            if (!request.isUserInRole(role.name())) {
                continue;
            }

            List<String> urlPatterns = SecurityConfig.getAllAppRoles().get(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get URL pattern from http request
     * pathInfo:
     * null ==> /
     * /users ==> /users
     * /users/31 ==> /users
     * /users/31/grades ==> /users/grades
     *
     * @param request http request
     * @return URL pattern
     */
    private static String getUrlPattern(HttpServletRequest request) {
        String urlPath = "/";

        if (request.getPathInfo() != null) {
            urlPath = request.getPathInfo().replaceAll("/\\d+", "");
        }

        return urlPath;
    }
}