package ua.training.admission.controller.commands;

import org.apache.log4j.Logger;
import ua.training.admission.model.services.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowSignupFormCommand extends CommandWrapper {

    private static final Logger LOGGER = Logger.getLogger(ShowSignupFormCommand.class);

    private static final String TITLE_HOME = "admission.title"; // TODO title for all pages
    private static final String TITLE_SIGNUP_FORM = ""; // FIXME

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_SIGNUP_FORM);
        return Paths.SIGNUP_JSP;
    }
}
