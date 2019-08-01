package ua.training.admission.security;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class SecurityUtils {

	private static int REDIRECT_ID = 0;
	private static final Map<Integer, String> id_uri_map = new HashMap<>();
	private static final Map<String, Integer> uri_id_map = new HashMap<>();

	/* Logger */
	private static final Logger log = Logger.getLogger(SecurityUtils.class);

	// Store user info in Session
	public static void storeLoggedUser(HttpSession session, User user) {
		// On the JSP can access via ${Attributes.PRINCIPAL}
		session.setAttribute(Attributes.PRINCIPAL, user);
	}

	// Get user info from Session
	public static User getLoggedUser(HttpSession session) {
		return (User) session.getAttribute(Attributes.PRINCIPAL);
	}

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

	public static String getRedirectAfterLoginUrl(int redirectId) {
		return id_uri_map.get(redirectId);
	}

	// Check whether this 'request' is required to login or not.
	public static boolean isSecurityPage(HttpServletRequest request) {
		String urlPattern = getUrlPattern(request);
		log.debug("# isSecurityPage # - urlPattern: " + urlPattern);

		return SecurityConfig.getAllAppRoles().values().stream()
				.flatMap(Collection::stream)
				.distinct()
				.anyMatch(urlPattern::equals);
	}

	// Check if this 'request' has a 'valid role'?
	public static boolean hasPermission(HttpServletRequest request) {
		String urlPattern = getUrlPattern(request);
		log.debug("$ hasPermission $ - urlPattern: " + urlPattern);

		Set<User.Role> allAppRoles = SecurityConfig.getAllAppRoles().keySet();

		for (User.Role role : allAppRoles) {
			if (!request.isUserInRole(role.name())) {
				log.debug("$ hasPermission $ - check request.isUserInRole(): " + role.name());
				continue;
			}

			log.debug("$ hasPermission $ - User In Role !");

			List<String> urlPatterns = SecurityConfig.getAllAppRoles().get(role);
			log.debug("$ hasPermission $ - urlPatterns: " + urlPatterns);
			if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
				return true;
			}
		}

		return false;
	}

	// pathInfo:
	// null ==> /
	// /users ==> /users
	// /users/31 ==> /users
	// /users/31/grades ==> /users/grades
	private static String getUrlPattern(HttpServletRequest request) {
		log.debug("getPathInfo: " + request.getPathInfo());
		String urlPath = "/";

		if (request.getPathInfo() != null) {
			urlPath = request.getPathInfo().replaceAll("/\\d+", "");
		}

		return urlPath;
	}
}