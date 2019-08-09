package ua.training.admission.controller.command.roles.all;

import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;
import ua.training.admission.view.I18n;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PageHome Command
 *
 * @author Oleksii Morenets
 */
public class PageHomeCommand extends CommandWrapper {

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_HOME);

        return Paths.HOME_JSP;
    }
}
