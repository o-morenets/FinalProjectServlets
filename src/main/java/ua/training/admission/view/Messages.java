package ua.training.admission.view;

/**
 * Application messages
 *
 * @author Oleksii Morenets
 */
public interface Messages {

    /* Exception messages */
    String APPLICATION_EXCEPTION = "Application exception";
    String DAO_FACTORY_EXCEPTION = "Dao Factory Exception";
    String CONNECTION_POOL_EXCEPTION = "Connection Pool Exception";
    String USER_ALREADY_EXISTS = "User already exists";
    String IO_EXCEPTION = "I/O Exception";
    String RESOURCE_NOT_FOUND_EXCEPTION = "Resource not found";
    String UNSUPPORTED_OPERATION_EXCEPTION = "Not implemented yet";
    String COUNT_AVERAGE_GRADE_EXCEPTION = "Error while count average grade";

    /* SQL Exception messages */
    String SQL_EXCEPTION = "SQL Error";
    String SQL_EXCEPTION_TRANSACTION_BEGIN = "Error begin transaction";
    String SQL_EXCEPTION_TRANSACTION_COMMIT = "Error commit transaction";
    String SQL_EXCEPTION_TRANSACTION_ROLLBACK = "Error rollback transaction";
    String SQL_EXCEPTION_CONNECTION_CLOSE = "Error close connection";

    /* Encrypt algorithm exception message */
    String ERROR_ENCRYPT_ALGORITHM = "No such algorithm for encrypt";

    /* Message for default 404 page */
    String REQUESTED_PAGE_NOT_FOUND = "Requested page not found: ";
}
