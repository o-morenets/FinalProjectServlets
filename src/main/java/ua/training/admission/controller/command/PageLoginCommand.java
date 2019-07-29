package ua.training.admission.controller.command;

import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PageLoginCommand
 */
public class PageLoginCommand extends CommandWrapper {

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_FORM_LOGIN);
        String paramRedirectId = request.getParameter(Parameters.REDIRECT_ID);
        request.setAttribute(Parameters.REDIRECT_ID, paramRedirectId);

        return Paths.LOGIN_JSP;
    }
}
