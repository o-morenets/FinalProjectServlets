package ua.training.admission.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interface Command
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
