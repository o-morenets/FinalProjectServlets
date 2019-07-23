package ua.training.admission.controller.command;

import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.admission.view.GlobalConstants.TITLE_FORM_LOGIN;

/**
 * PageLoginCommand
 */
public class PageLoginCommand extends CommandWrapper {

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_FORM_LOGIN);

        return Paths.LOGIN_JSP;
    }

}
