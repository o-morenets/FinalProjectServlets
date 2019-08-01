package ua.training.admission.controller.command;

import ua.training.admission.controller.exception.AppException;
import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.ResourceNotFoundException;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CommandWrapper for exception handling
 */
public abstract class CommandWrapper implements Command {

    /* Logger */
    private static final Logger log = Logger.getLogger(CommandWrapper.class);

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            return doExecute(request, response);
        } catch (ResourceNotFoundException rnfEx) {
            log.error(Messages.RESOURCE_NOT_FOUND_EXCEPTION, rnfEx);
            request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_ERROR);
            throw rnfEx;
        } catch (AppException appEx) {
            log.error(Messages.APPLICATION_EXCEPTION, appEx);
            request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_ERROR);
            throw appEx;
        }
    }

    /**
     * Execute nested command
     *
     * @param request  nested http request
     * @param response nested http response
     * @return nested command result
     * @throws ServletException when exception is thrown in nested method
     * @throws IOException when exception is thrown in nested method
     */
    abstract String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
