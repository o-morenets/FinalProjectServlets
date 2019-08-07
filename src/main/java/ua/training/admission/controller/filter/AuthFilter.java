package ua.training.admission.controller.filter;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;
import ua.training.admission.security.SecurityUtils;
import ua.training.admission.security.UserRoleRequestWrapper;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Authorization filter
 *
 * @author Oleksii Morenets
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    /* Logger */
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

        String pathInfo = request.getPathInfo();

        User loggedUser = SecurityUtils.getLoggedUser(request.getSession());

        if (pathInfo != null && pathInfo.equals(Paths.LOGIN)) {
            filterChain.doFilter(request, response);

            return;
        }

        HttpServletRequest wrapRequest = request;

        if (loggedUser != null) {
            String username = loggedUser.getUsername();
            List<Role> roles = new ArrayList<>(loggedUser.getRoles());
            wrapRequest = new UserRoleRequestWrapper(username, roles, request);
        }

        if (SecurityUtils.isSecurityPage(request)) {
            if (loggedUser == null) {
                String requestUri = request.getRequestURI();
                int redirectId = SecurityUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
                response.sendRedirect(wrapRequest.getContextPath() + wrapRequest.getServletPath() +
                        Paths.LOGIN + "?" + Parameters.REDIRECT_ID + "=" + redirectId);

                return;
            }

            if (!SecurityUtils.hasPermission(wrapRequest)) {
                request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_403);
                request.getServletContext().getRequestDispatcher(Paths.PAGE_403_JSP).forward(request, response);

                return;
            }
        }

        filterChain.doFilter(wrapRequest, response);
    }

    @Override
    public void destroy() {
    }
}
