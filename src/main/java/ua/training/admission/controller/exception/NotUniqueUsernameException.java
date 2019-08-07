package ua.training.admission.controller.exception;

/**
 * NotUniqueUsername Exception
 *
 * @author Oleksii Morenets
 */
public class NotUniqueUsernameException extends RuntimeException {

    public NotUniqueUsernameException(String message) {
        super(message);
    }
}
