package ua.training.admission.controller.commands;

import ua.training.admission.model.entities.User;
import ua.training.admission.model.services.UserService;
import org.apache.log4j.Logger;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand extends CommandWrapper {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static final String TITLE_HOME = "admission.title"; // TODO title for all pages

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter(Parameters.USERNAME);
        String password = request.getParameter(Parameters.PASSWORD);
        if (username != null && password != null) {
            Optional<User> user = userService.login(username, password);

            String contextPath = request.getContextPath();
            LOGGER.debug("contextPath: " + contextPath);

            if (user.isPresent()) {
                request.getSession().setAttribute(Attributes.USER, user.get());
                response.sendRedirect(contextPath + Paths.HOME); // FIXME path
            } else {
                request.setAttribute(Attributes.PAGE_TITLE, TITLE_HOME);
                response.sendRedirect(contextPath + Paths.HOME); // FIXME path
            }
        }
        return Paths.REDIRECTED;
    }
}
