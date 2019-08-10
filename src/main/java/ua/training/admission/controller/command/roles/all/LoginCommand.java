package ua.training.admission.controller.command.roles.all;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.security.EncryptPassword;
import ua.training.admission.security.SecurityUtils;
import ua.training.admission.view.Messages;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Login Command
 *
 * @author Oleksii Morenets
 */
public class LoginCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    /* Services */
    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter(Parameters.USERNAME);
        String password = request.getParameter(Parameters.PASSWORD);

        Optional<User> user = userService.login(username, EncryptPassword.encrypt(password));

        if (user.isPresent()) {
            redirectAfterLoginSuccessful(request, response, user.get());
        } else {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.LOGIN_ERROR);
        }

        return Paths.REDIRECTED;
    }

    /**
     * Perform actions when login is successful
     *
     * @param request  http request
     * @param response http response
     * @param user     logged user
     * @throws IOException if any i/o error occurs while redirect
     */
    private void redirectAfterLoginSuccessful(HttpServletRequest request, HttpServletResponse response, User user)
            throws IOException {

        SecurityUtils.storeLoggedUser(request.getSession(), user);

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter(Parameters.REDIRECT_ID));
        } catch (NumberFormatException ignored) {
        }

        String requestUri = SecurityUtils.getRedirectAfterLoginUrl(redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.HOME);
        }
    }
}
