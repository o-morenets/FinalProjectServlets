package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.security.EncryptPassword;
import ua.training.admission.security.SecurityUtils;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand extends CommandWrapper {

    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter(Parameters.USERNAME);
        String password = request.getParameter(Parameters.PASSWORD);

        if (username != null && password != null) {
            doLogin(request, response, username, password);
        }

        return Paths.REDIRECTED;
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response, String username, String password)
            throws IOException {

        Optional<User> user = userService.login(username, EncryptPassword.encrypt(password));
        String path = request.getContextPath() + request.getServletPath();

        if (user.isPresent()) {
            loginSuccessful(request, response, user.get());
        } else {
            response.sendRedirect(path + Paths.LOGIN_ERROR);
        }
    }

    private void loginSuccessful(HttpServletRequest request, HttpServletResponse response, User user)
            throws IOException {

        String path = request.getContextPath() + request.getServletPath();

        CommandUtils.setUserRole(request, user.getRole(), user.getUsername());
        SecurityUtils.storeLoggedUser(request.getSession(), user);

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception ignored) {
        }

        String requestUri = SecurityUtils.getRedirectAfterLoginUrl(redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            // Default after successful login
            response.sendRedirect(path + Paths.HOME);
        }
    }
}
