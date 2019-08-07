package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.service.UserService;
import ua.training.admission.utils.PaginationUtils;
import ua.training.admission.view.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserList Command
 *
 * @author Oleksii Morenets
 */
public class UserListCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(UserListCommand.class);

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_HOME);

        int currentPage = PaginationUtils.getParameterValue(request,
                Parameters.CURRENT_PAGE, Constants.DEFAULT_CURRENT_PAGE);

        int recordsPerPage = PaginationUtils.getParameterValue(request,
                Parameters.RECORDS_PER_PAGE, Constants.DEFAULT_RECORDS_PER_PAGE);

        int rows = userService.getNumberOfRowsByRole(Role.USER);
        int numPages = rows / recordsPerPage;
        if (numPages % recordsPerPage > 0) {
            numPages++;
        }

        request.setAttribute(Attributes.USERS, userService.findAllByRole(Role.USER, currentPage, recordsPerPage));
        request.setAttribute(Parameters.NUM_PAGES, numPages);
        request.setAttribute(Parameters.CURRENT_PAGE, currentPage);
        request.setAttribute(Parameters.RECORDS_PER_PAGE, recordsPerPage);

        return Paths.USERLIST_JSP;
    }
}
