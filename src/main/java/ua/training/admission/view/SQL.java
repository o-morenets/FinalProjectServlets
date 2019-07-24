package ua.training.admission.view;

public interface SQL {

    int SQL_CONSTRAINT_NOT_UNIQUE = 1062;


    /* User */
    String TABLE_USER = "usr";
    String TABLE_SPECIALITY = "speciality";

    //Fields
    String ID = "id";
    String USERNAME = "username";
    String PASSWORD = "password";
    String EMAIL = "email";
    String LAST_NAME = "last_name";
    String FIRST_NAME = "first_name";
    String SPECIALITY_ID = "speciality_id";
    String ROLE = "role";

    /* Speciality */
    String NAME = "name";

    //SQL
    String SELECT_USER_BY_USERNAME =
            "SELECT * FROM " + TABLE_USER + " WHERE lower(" + USERNAME + ") = ?";

    String SELECT_USER_BY_ID =
            "SELECT * FROM " + TABLE_USER + " WHERE " + ID + " = ?";

    String INSERT_INTO_USER =
            "INSERT INTO " + TABLE_USER +
            " (" + USERNAME + ", " + PASSWORD + ", " + EMAIL + ", " + FIRST_NAME + ", " +  LAST_NAME+ ", " + ROLE + ")" +
            " VALUES (?, ?, ?, ?, ?, ?)";

    String SELECT_USERS_BY_ROLE =
            "SELECT " + TABLE_USER + "." + ID +  ", " + USERNAME + ", " +  PASSWORD + ", " + EMAIL + ", " + ROLE + ", " +
                    FIRST_NAME + ", " + LAST_NAME + ", " + SPECIALITY_ID + ", " + NAME +
            " FROM " + TABLE_USER +
            " LEFT JOIN " + TABLE_SPECIALITY +
            " ON " + TABLE_USER + "." + SPECIALITY_ID + " = " + TABLE_SPECIALITY + "." + ID +
            " WHERE " + ROLE + " = ?";
}
