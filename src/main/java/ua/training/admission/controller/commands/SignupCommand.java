package ua.training.admission.controller.commands;

import org.apache.log4j.Logger;
import ua.training.admission.model.entities.User;
import ua.training.admission.model.services.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SignupCommand extends CommandWrapper {

    private static final Logger LOGGER = Logger.getLogger(SignupCommand.class);

    private static final String TITLE_HOME = "admission.title"; // TODO title for all pages

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        return Paths.REDIRECTED;
    }
}
