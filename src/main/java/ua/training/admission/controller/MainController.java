package ua.training.admission.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.training.admission.controller.commands.*;

import org.apache.log4j.Logger;
import ua.training.admission.view.Paths;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/api/*")
public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    private CommandHolder commandHolder;

    public MainController() {
        super();
        commandHolder = new CommandHolder();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandKey = getCommandKey(request);
        LOGGER.debug(commandKey);
        Command command = commandHolder.getCommand(commandKey);
        String viewPage = "";
        viewPage = command.execute(request, response);
        if (!viewPage.equals(Paths.REDIRECTED)) {
            LOGGER.debug("FORWARD to " + viewPage);
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }

    /**
     * Detect GET or POST command
     *
     * @param request
     * @return
     */
    private String getCommandKey(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI().replaceAll(".*/api", "");
        return method + ":" + path;
    }
}
