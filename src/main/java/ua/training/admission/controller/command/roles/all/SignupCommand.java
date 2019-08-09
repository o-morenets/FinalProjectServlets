package ua.training.admission.controller.command.roles.all;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.controller.exception.NotUniqueUsernameException;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.security.EncryptPassword;
import ua.training.admission.view.Messages;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.admission.view.Attributes.*;
import static ua.training.admission.view.Parameters.*;
import static ua.training.admission.view.I18n.*;

/**
 * Signup Command
 *
 * @author Oleksii Morenets
 */
public class SignupCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(SignupCommand.class);

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String passwordConfirm = request.getParameter(PASSWORD_2);
        String email = request.getParameter(EMAIL);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);

        boolean isFormValid = true;

        if (username.trim().isEmpty()) {
            request.setAttribute(USERNAME_ERROR, FORM_INVALID_USERNAME_EMPTY);
            isFormValid = false;
        }

        if (password.trim().isEmpty()) {
            request.setAttribute(PASSWORD_ERROR, FORM_INVALID_PASSWORD_EMPTY);
            isFormValid = false;
        }

        if (passwordConfirm.trim().isEmpty()) {
            request.setAttribute(PASSWORD_2_ERROR, FORM_INVALID_PASSWORD_RETYPE_EMPTY);
            isFormValid = false;
        }

        if (!password.equals(passwordConfirm)) {
            request.setAttribute(PASSWORD_ERROR, FORM_INVALID_PASSWORD_DIFFERENT);
            request.setAttribute(PASSWORD_2_ERROR, FORM_INVALID_PASSWORD_DIFFERENT);
            isFormValid = false;
        }

        if (email.trim().isEmpty()) {
            request.setAttribute(EMAIL_ERROR, FORM_INVALID_EMAIL_EMPTY);
            isFormValid = false;
        }

        // TODO @Email validation with RegEx

        if (firstName.trim().isEmpty()) {
            request.setAttribute(FIRST_NAME_ERROR, FORM_INVALID_FIRST_NAME);
            isFormValid = false;
        }

        if (lastName.trim().isEmpty()) {
            request.setAttribute(LAST_NAME_ERROR, FORM_INVALID_LAST_NAME);
            isFormValid = false;
        }

        User user = User.builder()
                .username(username)
                .password(EncryptPassword.encrypt(password))
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        if (!isFormValid) {
            request.setAttribute(PAGE_TITLE, TITLE_FORM_SIGNUP);
            request.setAttribute(USER, user);
            return Paths.SIGNUP_JSP;
        }

        try {
            userService.create(user);
            response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.LOGIN);

        } catch (NotUniqueUsernameException e) {
            log.warn(Messages.USER_ALREADY_EXISTS);
            request.setAttribute(PAGE_TITLE, TITLE_FORM_SIGNUP);
            request.setAttribute(USER, user);
            request.setAttribute(USERNAME_ERROR, FORM_INVALID_USERNAME_EXISTS);

            return Paths.SIGNUP_JSP;
        }

        return Paths.REDIRECTED;
    }
}
