package ua.training.admission.controller.filter;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.User;
import ua.training.admission.security.SecurityUtils;
import ua.training.admission.security.UserRoleRequestWrapper;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Paths;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath(); // FIXME
        log.debug("***** servletPath: " + servletPath);

        // User information stored in the Session.
        // (After successful login).
        User loggedUser = (User) request.getSession().getAttribute(Attributes.PRINCIPAL);
        log.debug("***** loggedUser: " + loggedUser);
        if (servletPath.equals(Paths.LOGIN)) {
            log.debug("***** servletPath = /login - return");
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequest wrapRequest = request;

        if (loggedUser != null) {
            // User Name
            String userName = loggedUser.getUsername();
            log.debug("***** loggedUser: " + loggedUser);

            // Roles
            List<User.Role> roles = loggedUser.getRoles();
            log.debug("***** roles: " + Arrays.toString(roles.toArray()));

            // Wrap old request by a new Request with userName and Roles information.
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }

        // Pages must be signed in.
        if (SecurityUtils.isSecurityPage(request)) {
            log.debug("***** SECURITY PAGE!");
            // If the user is not logged in, Redirect to the login page.
            if (loggedUser == null) {
                log.debug("***** loggedUser == null");

                String requestUri = request.getRequestURI();

                // Store the current page to redirect to after successful login.
                log.debug("***** Store the current page to redirect to after successful login...");
                int redirectId = SecurityUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                log.debug("***** sendRedirect -> login?redirectId=" + redirectId);
                response.sendRedirect(wrapRequest.getContextPath() + "/api//login?redirectId=" + redirectId); // FIXME
                return;
            }

            // Check if the user has a valid role?
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {
                log.warn("***** user has not valid role! - forward to 403 page...");
                request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_403);
                RequestDispatcher dispatcher =
                        request.getServletContext().getRequestDispatcher(Paths.PAGE_403_JSP);

                dispatcher.forward(request, response);
                return;
            }
        }

        filterChain.doFilter(wrapRequest, response);
    }

    @Override
    public void destroy() {
    }
}
