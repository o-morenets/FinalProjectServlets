package ua.training.admission.controller.command.roles.admin;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.service.UserService;
import ua.training.admission.model.validator.NumberValidator;
import ua.training.admission.view.*;

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

        NumberValidator numberValidator = new NumberValidator();

        String paramPassGrade = request.getParameter(Parameters.PASS_GRADE);
        if (!numberValidator.isValidDouble(paramPassGrade)) {
            request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_PASS_GRADE);
            request.setAttribute(Attributes.PASSGRADE_ERROR, I18n.FORM_INVALID_PASSGRADE);

            return Paths.PASS_GRADE_JSP;
        }

        int rows = userService.getNumberOfRowsByRole(Role.USER);
        Double passGrade = Double.parseDouble(paramPassGrade);
        userService.sendMessages(passGrade, 1, rows);

        response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.USERS_RATING_LIST);

        return Paths.REDIRECTED;
    }
}
