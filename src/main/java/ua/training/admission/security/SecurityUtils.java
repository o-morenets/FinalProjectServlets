package ua.training.admission.security;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.Attributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class SecurityUtils {

	private static int REDIRECT_ID = 0;
	private static final Map<Integer, String> id_uri_map = new HashMap<>();
	private static final Map<String, Integer> uri_id_map = new HashMap<>();
	private static final Logger log = Logger.getLogger(SecurityUtils.class);

	// Store user info in Session.
	public static void storeLoggedUser(HttpSession session, User principal) {
		// On the JSP can access via ${Attributes.PRINCIPAL}
		session.setAttribute(Attributes.PRINCIPAL, principal);
	}

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
		Set<User.Role> roles = SecurityConfig.getAllAppRoles();

		for (User.Role role : roles) {
			List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
			log.debug("# isSecurityPage # - getUrlPatternsForRole: " + Arrays.toString(urlPatterns.toArray()));

			if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
				return true;
			}
		}

		return false;
	}

	// Check if this 'request' has a 'valid role'?
	public static boolean hasPermission(HttpServletRequest request) {
		String urlPattern = getUrlPattern(request);

		Set<User.Role> allRoles = SecurityConfig.getAllAppRoles();

		for (User.Role role : allRoles) {
			if (!request.isUserInRole(role.name())) {
				continue;
			}

			List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
			if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
				return true;
			}
		}

		return false;
	}

	private static boolean hasUrlPattern(ServletContext servletContext, String urlPattern) {
		Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();

		for (String servletName : map.keySet()) {
			ServletRegistration sr = map.get(servletName);

			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}

	// servletPath:
	// ==> /spath
	// ==> /spath/*
	// ==> *.ext
	// ==> /
	private static String getUrlPattern(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();

		String urlPattern = null;
		if (pathInfo != null) {
			urlPattern = servletPath + "/*";
			return urlPattern;
		}
		urlPattern = servletPath;

		boolean has = hasUrlPattern(servletContext, urlPattern);
		if (has) {
			return urlPattern;
		}
		int i = servletPath.lastIndexOf('.');
		if (i != -1) {
			String ext = servletPath.substring(i + 1);
			urlPattern = "*." + ext;
			has = hasUrlPattern(servletContext, urlPattern);

			if (has) {
				return urlPattern;
			}
		}

		return "/";
	}
}