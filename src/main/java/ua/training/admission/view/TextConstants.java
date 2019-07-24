package ua.training.admission.view;

/**
 * TextConstants
 */
public interface TextConstants {

    /* Command methods */
    String GET = "GET:";
    String POST = "POST:";

    /* Page titles */
    String TITLE_HOME = "title.home";
    String TITLE_FORM_LOGIN = "title.form.login";
    String TITLE_FORM_SIGNUP = "title.form.signup";

    /* Form validation messages */
    String FORM_INVALID_USERNAME_EMPTY = "form.invalid.username.empty";
    String FORM_INVALID_PASSWORD_EMPTY = "form.invalid.password.empty";
    String FORM_INVALID_PASSWORD_RETYPE_EMPTY = "form.invalid.passwordRetype.empty";
    String FORM_INVALID_PASSWORD_DIFFERENT = "form.invalid.password.different";
    String FORM_INVALID_EMAIL_EMPTY = "form.invalid.email.empty";
    String FORM_INVALID_FIRST_NAME = "form.invalid.firstName";
    String FORM_INVALID_LAST_NAME = "form.invalid.lastName";
    String FORM_INVALID_USERNAME_EXISTS = "form.invalid.username.exists";

    String UTF_8 = "UTF-8";
    String COLON = ":";
}
