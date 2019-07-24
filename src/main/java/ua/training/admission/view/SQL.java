package ua.training.admission.view;

public interface SQL {

    int SQL_CONSTRAINT_NOT_UNIQUE = 1062;

    /* SQL */
    String SELECT_USER_BY_USERNAME = "SELECT * FROM usr WHERE lower(username) = ?";
    String SELECT_USER_BY_ID = "SELECT * FROM usr WHERE id = ?";
    String INSERT_INTO_USER = "INSERT INTO usr" +
            " (username, password, email, first_name, last_name, role)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    String SELECT_USERS_BY_ROLE = "SELECT * FROM usr WHERE role = ?";

    /* Fields */
    String ID = "id";
    String USERNAME = "username";
    String PASSWORD = "password";
    String EMAIL = "email";
    String LAST_NAME = "last_name";
    String FIRST_NAME = "first_name";
    String ROLE = "role";
}
