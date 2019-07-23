package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.admission.view.GlobalConstants.TITLE_FORM_SIGNUP;

public class PageSignupCommand extends CommandWrapper {

    private static final Logger LOG = Logger.getLogger(PageSignupCommand.class);

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_FORM_SIGNUP);

        return Paths.SIGNUP_JSP;
    }
}
