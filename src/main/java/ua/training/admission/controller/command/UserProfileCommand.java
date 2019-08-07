package ua.training.admission.controller.command;

import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.security.SecurityUtils;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserProfile Command
 *
 * @author Oleksii Morenets
 */
public class UserProfileCommand extends CommandWrapper {

    private UserService userService = UserService.getInstance();

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = SecurityUtils.getLoggedUser(request.getSession());

        return Paths.SERVLET_PATH + Paths.USERS_ + loggedUser.getId() + Paths.GRADES;
    }
}
