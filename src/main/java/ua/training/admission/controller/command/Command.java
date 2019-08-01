package ua.training.admission.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface Command
 */
public interface Command {

    /**
     * Executes actions according to Command pattern
     *
     * @param request  http request
     * @param response http response
     * @return uri path for command
     * @throws ServletException when servlet exception occurs
     * @throws IOException when I/O exception occurs
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
