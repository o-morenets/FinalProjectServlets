package ua.training.admission.controller.command.roles.all;

import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.security.SecurityUtils;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout Command
 *
 * @author Oleksii Morenets
 */
public class LogoutCommand extends CommandWrapper {

    @Override
    protected String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SecurityUtils.storeLoggedUser(request.getSession(), null);
        response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.LOGIN_LOGOUT);

        return Paths.REDIRECTED;
    }
}
