package ua.training.admission.controller.command;

import ua.training.admission.controller.exception.AppException;
import org.apache.log4j.Logger;
import ua.training.admission.view.Attributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CommandWrapper for exceptions handling
 */
public abstract class CommandWrapper implements Command {

    private static final Object ERROR = "error";
    private static final Logger LOG = Logger.getLogger(CommandWrapper.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            return doExecute(request, response);
        } catch (AppException e) {
            System.out.println(e.getCause().toString());
            LOG.error(e);
            request.setAttribute(Attributes.PAGE_TITLE, ERROR);
            throw e;
        }
    }

    abstract String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
