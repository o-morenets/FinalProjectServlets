package ua.training.admission.controller;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.training.admission.controller.command.*;

import org.apache.log4j.Logger;
import ua.training.admission.model.entity.User;
import ua.training.admission.view.*;

/**
 * Servlet implementation class MainController
 */
@WebServlet(Paths.SERVLET_PATH + "/*")
public class MainController extends HttpServlet {

    /* Logger */
    private static final Logger log = Logger.getLogger(MainController.class);

    private CommandHolder commandHolder;

    public MainController() {
        super();
        commandHolder = new CommandHolder();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute(Attributes.LOGGED_USERS, new HashSet<User>());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String commandKey = CommandUtils.getCommandKey(request);
        log.debug(commandKey);
        Command command = commandHolder.getCommand(commandKey);
        String viewPage = command.execute(request, response);
        if (!viewPage.equals(Paths.REDIRECTED)) {
            log.debug("forward to: " + viewPage);
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }
}
