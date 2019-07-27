package ua.training.admission.controller.command;

import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCommand extends CommandWrapper {

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Attributes.USER_ID, request.getPathInfo().replaceAll("\\D+", ""));

        return Paths.SERVLET_PATH + request.getPathInfo().replaceAll(".*\\d+", "");
    }
}
