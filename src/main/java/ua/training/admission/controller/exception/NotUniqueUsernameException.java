package ua.training.admission.controller.exception;

public class NotUniqueUsernameException extends RuntimeException {

    public NotUniqueUsernameException(String message) {
        super(message);
    }
}
