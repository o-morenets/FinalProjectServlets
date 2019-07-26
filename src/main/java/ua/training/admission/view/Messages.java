package ua.training.admission.view;

public interface Messages {

    String AppException = "Application exception";
    String NAMING_EXCEPTION = "Naming Exception";
    String DAO_FACTORY_EXCEPTION = "Dao Factory Exception";
    String USER_ALREADY_EXISTS = "User already exists";

    /* SQL Exceptions */
    String SQL_EXCEPTION = "SQL Error";
    String SQL_EXCEPTION_TRANSACTION_BEGIN = "Error begin transaction";
    String SQL_EXCEPTION_TRANSACTION_COMMIT = "Error commit transaction";
    String SQL_EXCEPTION_TRANSACTION_ROLLBACK = "Error rollback transaction";
    String SQL_EXCEPTION_CONNECTION_CLOSE = "Error close connection";

    String ERROR_ENCRYPT_ALGORITHM = "No such algorithm for encrypt";
}
