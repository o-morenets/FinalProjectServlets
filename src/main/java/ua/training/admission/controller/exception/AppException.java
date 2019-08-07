package ua.training.admission.controller.exception;

/**
 * AppException - for any unhandled Exception
 *
 * @author Oleksii Morenets
 */
public class AppException extends RuntimeException {

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
