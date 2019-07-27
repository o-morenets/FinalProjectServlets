package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.security.EncryptPassword;
import ua.training.admission.view.Attributes;
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
            Optional<User> user = userService.login(username, EncryptPassword.encrypt(password));

            String contextPath = request.getContextPath();

            if (user.isPresent()) {
                User usr = user.get();
                if (CommandUtils.checkUserIsLogged(request, usr.getUsername())) {
                    response.sendRedirect(contextPath + Paths.LOGIN_AUTHORIZED);
                } else {
                    CommandUtils.setUserRole(request, usr.getRole(), usr.getUsername());
                    request.getSession().setAttribute(Attributes.PRINCIPAL, usr);
                    response.sendRedirect(contextPath + Paths.HOME);
                }
            } else {
                CommandUtils.setUserRole(request, User.Role.GUEST, "Guest");
                response.sendRedirect(contextPath + Paths.LOGIN_ERROR);
            }
        }

        return Paths.REDIRECTED;
    }
}
