package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.NotUniqueUsernameException;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.security.EncryptPassword;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.admission.view.Attributes.*;
import static ua.training.admission.view.Parameters.*;
import static ua.training.admission.view.TextConstants.*;

public class SignupCommand extends CommandWrapper {

    private static final Logger LOG = Logger.getLogger(SignupCommand.class);

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String passwordConfirm = request.getParameter(PASSWORD_2);
        String email = request.getParameter(EMAIL);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);

        boolean isFormValid = true;

        // TODO green text -> psfs

        if (username.trim().isEmpty()) {
            request.setAttribute(USERNAME_ERROR, FORM_INVALID_USERNAME_EMPTY);
            isFormValid = false;
        }

        if (password.trim().isEmpty()) {
            request.setAttribute(PASSWORD_ERROR, FORM_INVALID_PASSWORD_EMPTY);
            isFormValid = false;
        }

        boolean isConfirmEmpty = passwordConfirm.trim().isEmpty();
        if (isConfirmEmpty) {
            request.setAttribute(PASSWORD_2_ERROR, FORM_INVALID_PASSWORD_RETYPE_EMPTY);
            isFormValid = false;
        }

        boolean passwordsDifferent = !password.equals(passwordConfirm);
        if (passwordsDifferent) {
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
            userService.createUser(user);
            response.sendRedirect(request.getContextPath() + Paths.API_LOGIN);

        } catch (NotUniqueUsernameException ex) {
            request.setAttribute(PAGE_TITLE, TITLE_FORM_SIGNUP);
            request.setAttribute(USER, user);
            request.setAttribute(USERNAME_ERROR, FORM_INVALID_USERNAME_EXISTS);
            return Paths.SIGNUP_JSP;
        }

        return Paths.REDIRECTED;
    }
}
