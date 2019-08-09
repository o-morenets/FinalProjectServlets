package ua.training.admission.controller.command.roles.admin;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.controller.command.roles.user.UpdateSpecialityCommand;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.service.SubjectGradeService;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SendMessages Command
 *
 * @author Oleksii Morenets
 */
public class SendMessagesCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(SendMessagesCommand.class);

    private UserService userService = UserService.getInstance();

    @Override
    protected String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Double  passGrade = Double.valueOf(request.getParameter(Parameters.PASS_GRADE));
        int rows = userService.getNumberOfRowsByRole(Role.USER);
        userService.sendMessages(passGrade, 1, rows);

        response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.USERS_RATING_LIST);

        return Paths.REDIRECTED;
    }
}
