package ua.training.admission.controller.command;

import ua.training.admission.model.entity.User;
import ua.training.admission.security.SecurityUtils;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;
import ua.training.admission.view.TextConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand extends CommandWrapper {

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SecurityUtils.storeLoggedUser(request.getSession(), null);
        response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.LOGIN_LOGOUT);

        return Paths.REDIRECTED;
    }
}
