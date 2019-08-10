package ua.training.admission.model.validators;

import ua.training.admission.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static ua.training.admission.view.Attributes.PAGE_TITLE;
import static ua.training.admission.view.Attributes.USER;
import static ua.training.admission.view.I18n.TITLE_FORM_SIGNUP;

public abstract class EntityValidator<T> implements Validator<T> {

    /**
     * Error map
     */
    private Map<String, String> errors = new HashMap<>();

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    /**
     * Adds error to the error map
     *
     * @param errorAttribute error name
     * @param errorValue     error text or error key to the message bundle
     */
    public void addError(String errorAttribute, String errorValue) {
        errors.put(errorAttribute, errorValue);
    }

    /**
     * Validates user input via RegEx
     *
     * @param inputString    user input
     * @param regEx          regEx pattern
     * @param errorAttribute error attribute
     * @param errorValue     error value
     */
    void validateWithRegex(String inputString, String regEx, String errorAttribute, String errorValue) {
        if (!Pattern.compile(regEx).matcher(inputString).matches()) {
            addError(errorAttribute, errorValue);
        }
    }

    /**
     * Set Error attributes for jsp
     *
     * @param request http request
     */
    public void setErrorAttributes(HttpServletRequest request) {
        errors.forEach(request::setAttribute);
    }
}
