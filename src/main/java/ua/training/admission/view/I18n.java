package ua.training.admission.view;

/**
 * TextConstants
 */
public interface I18n {

    /* Page titles */
    String TITLE_HOME = "title.home";
    String TITLE_FORM_LOGIN = "title.login";
    String TITLE_FORM_SIGNUP = "title.signup";
    String TITLE_SPECIALITIES = "title.userSpeciality";
    String TITLE_GRADES = "title.grades";
    String TITLE_403 = "title.error.403";
    String TITLE_404 = "title.error.404";
    String TITLE_ERROR = "title.error.throwable";

    /* Form validation messages */
    String FORM_INVALID_USERNAME_EMPTY = "form.invalid.username.empty";
    String FORM_INVALID_USERNAME_EXISTS = "form.invalid.username.exists";
    String FORM_INVALID_PASSWORD_EMPTY = "form.invalid.password.empty";
    String FORM_INVALID_PASSWORD_RETYPE_EMPTY = "form.invalid.passwordRetype.empty";
    String FORM_INVALID_PASSWORD_DIFFERENT = "form.invalid.password.different";
    String FORM_INVALID_EMAIL_EMPTY = "form.invalid.email.empty";
    String FORM_INVALID_EMAIL_INCORRECT = "form.invalid.email.incorrect";
    String FORM_INVALID_FIRST_NAME = "form.invalid.firstName";
    String FORM_INVALID_LAST_NAME = "form.invalid.lastName";
}