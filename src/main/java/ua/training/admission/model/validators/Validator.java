package ua.training.admission.model.validators;

public interface Validator<T> {

    /* Regular Expressions */
    String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validates an entity of type T by adding errors to the error map
     *
     * @param t entity of tpe T
     */
    void validate(T t);

    /**
     * Checks whether errors are present in error map
     *
     * @return true if at least one error is present in error map, false otherwise
     */
    boolean hasErrors();
}
