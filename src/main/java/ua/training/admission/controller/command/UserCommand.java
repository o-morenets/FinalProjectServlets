package ua.training.admission.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCommand extends CommandWrapper {

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getPathInfo().replaceAll(".*\\d+", "");
    }
}
