package ua.training.admission.controller.command.roles.all;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.controller.exception.NotUniqueUsernameException;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.model.validators.UserValidator;
import ua.training.admission.security.EncryptPassword;
import ua.training.admission.view.Messages;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

        UserValidator userValidator = new UserValidator();

        User user = User.builder()
                .username(request.getParameter(USERNAME))
                .password(request.getParameter(PASSWORD))
                .email(request.getParameter(EMAIL))
                .firstName(request.getParameter(FIRST_NAME))
                .lastName(request.getParameter(LAST_NAME))
                .build();

        userValidator.validate(user);

        String passwordConfirm = request.getParameter(PASSWORD_2);

        boolean isConfirmEmpty = passwordConfirm.trim().isEmpty();
        if (isConfirmEmpty) {
            userValidator.addError(PASSWORD_2_ERROR, FORM_INVALID_PASSWORD_RETYPE_EMPTY);
        }

        boolean passwordsDifferent = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);
        if (passwordsDifferent) {
            userValidator.addError(PASSWORD_ERROR, FORM_INVALID_PASSWORD_DIFFERENT);
            userValidator.addError(PASSWORD_2_ERROR, FORM_INVALID_PASSWORD_DIFFERENT);
        }

        if (isConfirmEmpty || passwordsDifferent || userValidator.hasErrors()) {
            setErrorAttributes(request, user, userValidator.getErrors());

            return Paths.SIGNUP_JSP;
        }

        try {
            user.setPassword(EncryptPassword.encrypt(user.getPassword()));
            userService.create(user);
            response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.LOGIN);

        } catch (NotUniqueUsernameException e) {
            log.warn(Messages.USER_ALREADY_EXISTS);

            userValidator.addError(USERNAME_ERROR, FORM_INVALID_USERNAME_EXISTS);
            setErrorAttributes(request, user, userValidator.getErrors());

            return Paths.SIGNUP_JSP;
        }

        return Paths.REDIRECTED;
    }

    private void setErrorAttributes(HttpServletRequest request, User user, Map<String, String> errors) {
        errors.forEach(request::setAttribute);
        request.setAttribute(PAGE_TITLE, TITLE_FORM_SIGNUP);
        request.setAttribute(USER, user);
    }
}
