package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PageHomeCommand
 */
public class UserListCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(UserListCommand.class);

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_HOME);

        String paramCurrentPage = request.getParameter(Parameters.CURRENT_PAGE);
        String paramRecordsPerPage = request.getParameter(Parameters.RECORDS_PER_PAGE);

        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(paramCurrentPage);
        } catch (NumberFormatException e) {
            log.error(Messages.NUMBER_FORMAT_EXCEPTION, e);
        }

        int recordsPerPage = 10; // FIXME
        try {
            recordsPerPage = Integer.parseInt(paramRecordsPerPage);
        } catch (NumberFormatException e) {
            log.error(Messages.NUMBER_FORMAT_EXCEPTION, e);
        }

        int rows = userService.getNumberOfRowsByRole(User.Role.USER);
        int numPages = rows / recordsPerPage;
        if (numPages % recordsPerPage > 0) {
            numPages++;
        }

        request.setAttribute(Attributes.USERS, userService.findAllByRole(User.Role.USER, currentPage, recordsPerPage));
        request.setAttribute(Parameters.NUM_PAGES, numPages);
        request.setAttribute(Parameters.CURRENT_PAGE, currentPage);
        request.setAttribute(Parameters.RECORDS_PER_PAGE, recordsPerPage);

        return Paths.USERLIST_JSP;
    }
}
