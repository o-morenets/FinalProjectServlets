package ua.training.admission.view;

/**
 * TextConstants for i18n
 *
 * @author Oleksii Morenets
 */
public interface I18n {

    /* Page titles */
    String TITLE_HOME = "title.home";
    String TITLE_FORM_LOGIN = "title.login";
    String TITLE_FORM_SIGNUP = "title.signup";
    String TITLE_SPECIALITIES = "title.userSpeciality";
    String TITLE_GRADES = "title.grades";
    String TITLE_PASS_GRADE = "title.passGrade";
    String TITLE_USER_LIST = "title.userList";
    String TITLE_RATING_LIST = "title.ratingList";
    String TITLE_403 = "title.error.403.Forbidden";
    String TITLE_404 = "title.error.404.NotFound";
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
    String FORM_INVALID_INTEGER = "form.invalid.integer";
    String FORM_INVALID_PASSGRADE = "form.invalid.passGrade";
}
