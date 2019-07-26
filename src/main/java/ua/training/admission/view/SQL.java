package ua.training.admission.view;

public interface SQL {

    /* SQL 'not unique' constraint code */
    int SQL_CONSTRAINT_NOT_UNIQUE = 1062;

    /* Table names */
    String TABLE_USER = "usr";
    String TABLE_SPECIALITY = "speciality";
    String TABLE_SUBJECT = "subject";
    String TABLE_SUBJECT_GRADE = "subject_grade";
    String TABLE_SPECIALITY_SUBJECT = "speciality_subject";


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
    // SpecialitySubject
    String SPECIALITY_SUBJECT_SPECIALITY_ID = "speciality_id";
    String SPECIALITY_SUBJECT_SUBJECT_ID = "subject_id";
    // SubjectGrade
    String SUBJECT_GRADE_ID = "id";
    String SUBJECT_GRADE_USER_ID = "user_id";
    String SUBJECT_GRADE_SUBJECT_ID = "subject_id";
    String SUBJECT_GRADE_GRADE = "grade";

    
    /* SQL */

    // User
    String SELECT_USER_BY_USERNAME =
            "SELECT * FROM " + TABLE_USER + " WHERE " + USER_USERNAME + " = ?";
    String SELECT_USER_BY_ID =
            "SELECT " + TABLE_USER + "." + USER_ID +  ", " + USER_USERNAME + ", " + USER_PASSWORD +
                    ", " + USER_EMAIL + ", " + USER_ROLE + ", " + USER_FIRST_NAME +
                    ", " + USER_LAST_NAME + ", " + USER_SPECIALITY_ID + ", " + SPECIALITY_NAME +
                    " FROM " + TABLE_USER +
                    " LEFT JOIN " + TABLE_SPECIALITY +
                    " ON " + TABLE_USER + "." + USER_SPECIALITY_ID + " = " + TABLE_SPECIALITY + "." + SPECIALITY_ID +
                    " WHERE " + TABLE_USER + "." + USER_ID + " = ?";
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
    String UPDATE_USER =
            "UPDATE " + TABLE_USER + " SET " + USER_SPECIALITY_ID + " = ? WHERE " + USER_ID + " = ?";

    // Speciality
    String SELECT_SPECIALITIES =
            "SELECT * FROM " + TABLE_SPECIALITY;
    String SELECT_SPECIALITY_BY_ID =
            "SELECT * FROM " + TABLE_SPECIALITY + " WHERE " + SPECIALITY_ID + " = ?";

    // SubjectGrade
    String SELECT_USER_SUBJECT_GRADES =
            "SELECT " + TABLE_SUBJECT + "." + SUBJECT_ID + ", " +
                    TABLE_SUBJECT + "." + SUBJECT_NAME + ", " +
                    TABLE_SUBJECT_GRADE + "." + SUBJECT_GRADE_GRADE +
            " FROM " + TABLE_USER +
            " JOIN " + TABLE_SPECIALITY +
            " ON " + TABLE_USER + "." + USER_SPECIALITY_ID + " = " + TABLE_SPECIALITY + "." + SPECIALITY_ID +
            " JOIN " + TABLE_SPECIALITY_SUBJECT +
            " ON " + TABLE_SPECIALITY_SUBJECT + "." + SPECIALITY_SUBJECT_SPECIALITY_ID +
                    " = " + TABLE_SPECIALITY + "." + SPECIALITY_ID +
            " LEFT JOIN " + TABLE_SUBJECT +
            " ON " + TABLE_SPECIALITY_SUBJECT + "." + SPECIALITY_SUBJECT_SUBJECT_ID +
                    " = " + TABLE_SUBJECT + "." + SUBJECT_ID +
            " LEFT JOIN " + TABLE_SUBJECT_GRADE +
            " ON " + TABLE_SUBJECT_GRADE + "." + SUBJECT_GRADE_SUBJECT_ID + " = " + TABLE_SUBJECT + "." + SUBJECT_ID +
                    " AND " + TABLE_SUBJECT_GRADE + "." + SUBJECT_GRADE_USER_ID + " = " + TABLE_USER + "." + USER_ID +
            " WHERE " + TABLE_USER + "." + USER_ID + " = ?";
}
