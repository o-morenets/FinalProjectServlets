package ua.training.admission.controller.command.roles.admin;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.service.UserService;
import ua.training.admission.utils.PaginationUtils;
import ua.training.admission.view.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RatingList Command
 *
 * @author Oleksii Morenets
 */
public class RatingListCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(RatingListCommand.class);

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_RATING_LIST);

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

        return Paths.RATING_LIST_JSP;
    }
}
