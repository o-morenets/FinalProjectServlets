package ua.training.admission.controller.command;

import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;
import ua.training.admission.view.TextConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PageHomeCommand
 */
public class PageHomeCommand extends CommandWrapper {

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, TextConstants.TITLE_HOME);

        return Paths.HOME_JSP;
    }
}
