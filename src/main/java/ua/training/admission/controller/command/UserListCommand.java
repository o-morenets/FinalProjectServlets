package ua.training.admission.controller.command;

import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;
import ua.training.admission.view.I18n;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PageHomeCommand
 */
public class UserListCommand extends CommandWrapper {

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_HOME);
        request.setAttribute(Attributes.USERS, userService.findAllByRole(User.Role.USER));

        return Paths.USERLIST_JSP;
    }
}
