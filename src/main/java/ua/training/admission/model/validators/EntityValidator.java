package ua.training.admission.model.validators;

import ua.training.admission.model.entity.User;

import java.util.HashMap;
import java.util.Map;

public abstract class EntityValidator<T> implements Validator<T> {

    /**
     * Error map
     */
    Map<String, String> errors = new HashMap<>();

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

    public Map<String, String> getErrors() {
        return errors;
    }
}
