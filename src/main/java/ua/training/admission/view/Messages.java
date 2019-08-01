package ua.training.admission.view;

public interface Messages {

    String APPLICATION_EXCEPTION = "Application exception";
    String NAMING_EXCEPTION = "Naming Exception";
    String DAO_FACTORY_EXCEPTION = "Dao Factory Exception";
    String USER_ALREADY_EXISTS = "User already exists";
    String NUMBER_FORMAT_EXCEPTION = "Number Format Exception while parsing a number";
    String IO_EXCEPTION = "I/O Exception";
    String RESOURCE_NOT_FOUND_EXCEPTION = "Resource not found";

    /* SQL Exceptions */
    String SQL_EXCEPTION = "SQL Error";
    String SQL_EXCEPTION_TRANSACTION_BEGIN = "Error begin transaction";
    String SQL_EXCEPTION_TRANSACTION_COMMIT = "Error commit transaction";
    String SQL_EXCEPTION_TRANSACTION_ROLLBACK = "Error rollback transaction";
    String SQL_EXCEPTION_CONNECTION_CLOSE = "Error close connection";

    String ERROR_ENCRYPT_ALGORITHM = "No such algorithm for encrypt";
}
