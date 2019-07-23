package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.NotUniqueUsernameException;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.training.admission.view.GlobalConstants.TITLE_FORM_SIGNUP;

public class SignupCommand extends CommandWrapper {

    private static final Logger LOG = Logger.getLogger(SignupCommand.class);

    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter(Parameters.USERNAME);
        String password = request.getParameter(Parameters.PASSWORD);
        String passwordConfirm = request.getParameter(Parameters.PASSWORD_2);
        String email = request.getParameter(Parameters.EMAIL);
        String firstName = request.getParameter(Parameters.FIRSTNAME);
        String lastName = request.getParameter(Parameters.LASTNAME);

        boolean isFormValid = true;

        // TODO green text -> psfs

        if (username.trim().isEmpty()) {
            request.setAttribute("usernameError", "form.invalid.username.empty");
            isFormValid = false;
        }

        if (password.trim().isEmpty()) {
            request.setAttribute("passwordError", "form.invalid.password.empty");
            isFormValid = false;
        }

        boolean isConfirmEmpty = passwordConfirm.trim().isEmpty();
        if (isConfirmEmpty) {
            request.setAttribute("password2Error", "form.invalid.passwordRetype.empty");
            isFormValid = false;
        }

        boolean passwordsDifferent = !password.equals(passwordConfirm);
        if (passwordsDifferent) {
            request.setAttribute("passwordError", "form.invalid.password.different");
            request.setAttribute("password2Error", "form.invalid.password.different");
            isFormValid = false;
        }

        if (email.trim().isEmpty()) {
            request.setAttribute("emailError", "form.invalid.email.empty");
            isFormValid = false;
        }

        // TODO @Email validation with RegEx

        if (firstName.trim().isEmpty()) {
            request.setAttribute("firstNameError", "form.invalid.firstName");
            isFormValid = false;
        }

        if (lastName.trim().isEmpty()) {
            request.setAttribute("lastNameError", "form.invalid.lastName");
            isFormValid = false;
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        if (!isFormValid) {
            request.setAttribute(Attributes.PAGE_TITLE, TITLE_FORM_SIGNUP);
            request.setAttribute(Attributes.USER, user);
            return Paths.SIGNUP_JSP;
        }

        try {
            userService.createUser(user);
            response.sendRedirect(request.getContextPath() + Paths.API_LOGIN);

        } catch (NotUniqueUsernameException ex) {
            request.setAttribute(Attributes.PAGE_TITLE, TITLE_FORM_SIGNUP);
            request.setAttribute(Attributes.USER, user);
            request.setAttribute("usernameError", "form.invalid.username.exists");
            return Paths.SIGNUP_JSP;
        }

        return Paths.REDIRECTED;
    }
}
