package ua.training.admission.model.validator;

import ua.training.admission.model.entity.User;

import static ua.training.admission.view.Attributes.*;
import static ua.training.admission.view.I18n.*;

public class UserValidator extends EntityValidator<User> {

    @Override
    public void validate(User user) {
        if (user.getUsername().trim().isEmpty()) {
            addError(USERNAME_ERROR, FORM_INVALID_USERNAME_EMPTY);
        }

        if (user.getPassword().trim().isEmpty()) {
            addError(PASSWORD_ERROR, FORM_INVALID_PASSWORD_EMPTY);
        }

        String email = user.getEmail().trim();
        if (email.isEmpty()) {
            addError(EMAIL_ERROR, FORM_INVALID_EMAIL_EMPTY);
        }

        validateWithRegex(email, REGEX_EMAIL, EMAIL_ERROR, FORM_INVALID_EMAIL_INCORRECT);

        if (user.getFirstName().trim().isEmpty()) {
            addError(FIRST_NAME_ERROR, FORM_INVALID_FIRST_NAME);
        }

        if (user.getFirstName().trim().isEmpty()) {
            addError(LAST_NAME_ERROR, FORM_INVALID_LAST_NAME);
        }
    }
}
