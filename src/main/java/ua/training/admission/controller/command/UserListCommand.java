package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
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

    private static final Logger log = Logger.getLogger(UserListCommand.class);
    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_HOME);

        String paramCurrentPage = request.getParameter("currentPage");
        String paramRecordsPerPage = request.getParameter("recordsPerPage");
        log.debug(paramCurrentPage + " & " + paramRecordsPerPage);

        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(paramCurrentPage);
        } catch (NumberFormatException ignored) {
        }

        int recordsPerPage = 10;
        try {
            recordsPerPage = Integer.parseInt(paramRecordsPerPage);
        } catch (NumberFormatException ignored) {
        }

        int rows = userService.getNumberOfRowsByRole(User.Role.USER);
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute(Attributes.USERS, userService.findAllByRole(User.Role.USER, currentPage, recordsPerPage));
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return Paths.USERLIST_JSP;
    }
}
