package ua.training.admission.view;

public interface SQL {

    int SQL_CONSTRAINT_NOT_UNIQUE = 1062;

    /* Table names */
    String TABLE_SPECIALITY = "speciality";
    String TABLE_USER = "usr";

    /* Fields */

    // User
    String USER_ID = "id";
    String USER_USERNAME = "username";
    String USER_PASSWORD = "password";
    String USER_EMAIL = "email";
    String USER_LAST_NAME = "last_name";
    String USER_FIRST_NAME = "first_name";
    String USER_SPECIALITY_ID = "speciality_id";
    String USER_ROLE = "role";
    // Speciality
    String SPECIALITY_ID = "id";
    String SPECIALITY_NAME = "name";
    // Subject
    String SUBJECT_ID = "id";
    String SUBJECT_NAME = "name";
    // SubjectGrade
    String SUBJECT_GRADE_ID = "id";
    String SUBJECT_GRADE_USER_ID = "user_id";
    String SUBJECT_GRADE_SUBJECT_ID = "subject_id";
    String SUBJECT_GRADE_GRADE = "grade";

    /* SQL */

    // User
    String SELECT_USER_BY_USERNAME =
            "SELECT * FROM " + TABLE_USER + " WHERE lower(" + USER_USERNAME + ") = ?";
    String SELECT_USER_BY_ID =
            "SELECT * FROM " + TABLE_USER + " WHERE " + USER_ID + " = ?";
    String INSERT_INTO_USER =
            "INSERT INTO " + TABLE_USER +
            " (" + USER_USERNAME + ", " + USER_PASSWORD + ", " + USER_EMAIL +
                    ", " + USER_FIRST_NAME + ", " + USER_LAST_NAME + ", " + USER_ROLE + ")" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    String SELECT_USERS_BY_ROLE =
            "SELECT " + TABLE_USER + "." + USER_ID +  ", " + USER_USERNAME + ", " + USER_PASSWORD +
                    ", " + USER_EMAIL + ", " + USER_ROLE + ", " + USER_FIRST_NAME +
                    ", " + USER_LAST_NAME + ", " + USER_SPECIALITY_ID + ", " + SPECIALITY_NAME +
            " FROM " + TABLE_USER +
            " LEFT JOIN " + TABLE_SPECIALITY +
            " ON " + TABLE_USER + "." + USER_SPECIALITY_ID + " = " + TABLE_SPECIALITY + "." + SPECIALITY_ID +
            " WHERE " + USER_ROLE + " = ?";

    // Speciality
    String SELECT_SPECIALITIES = "SELECT * FROM " + TABLE_SPECIALITY;
    String SELECT_SPECIALITY_BY_ID = ""; // TODO
}
