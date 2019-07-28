package ua.training.admission.view;
// TODO - make separate files
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

    String ID = "id";

    // User
    String USER_ID = TABLE_USER + "." + ID;
    String USER_USERNAME = TABLE_USER + "." + "username";
    String USER_PASSWORD = TABLE_USER + "." + "password";
    String USER_EMAIL = TABLE_USER + "." + "email";
    String USER_LAST_NAME = TABLE_USER + "." + "last_name";
    String USER_FIRST_NAME = TABLE_USER + "." + "first_name";
    String USER_SPECIALITY_ID = TABLE_USER + "." + "speciality_id";
    String USER_ROLE = TABLE_USER + "." + "role";

    // Speciality
    String SPECIALITY_ID = TABLE_SPECIALITY + "." + ID;
    String SPECIALITY_NAME = TABLE_SPECIALITY + "." + "name";

    // Subject
    String SUBJECT_ID = TABLE_SUBJECT + "." + ID;
    String SUBJECT_NAME = TABLE_SUBJECT + "." + "name";

    // SpecialitySubject
    String SPECIALITY_SUBJECT_SPECIALITY_ID = TABLE_SPECIALITY_SUBJECT + "." + "speciality_id";
    String SPECIALITY_SUBJECT_SUBJECT_ID = TABLE_SPECIALITY_SUBJECT + "." + "subject_id";

    // SubjectGrade
    String SUBJECT_GRADE_USER_ID = TABLE_SUBJECT_GRADE + "." + "user_id";
    String SUBJECT_GRADE_SUBJECT_ID = TABLE_SUBJECT_GRADE + "." + "subject_id";
    String SUBJECT_GRADE_GRADE = TABLE_SUBJECT_GRADE + "." + "grade";


    /* SQL */

    // User
    String SELECT_USER_BY_USERNAME =
            "SELECT * FROM " + TABLE_USER + " WHERE " + USER_USERNAME + " = ?";
    String SELECT_USER_BY_ID =
            "SELECT " + USER_ID +  ", " + USER_USERNAME + ", " + USER_PASSWORD +
            ", " + USER_EMAIL + ", " + USER_ROLE + ", " + USER_FIRST_NAME +
            ", " + USER_LAST_NAME + ", " + USER_SPECIALITY_ID + ", " + SPECIALITY_NAME +
            " FROM " + TABLE_USER +
            " LEFT JOIN " + TABLE_SPECIALITY +
            " ON " + USER_SPECIALITY_ID + " = " + SPECIALITY_ID +
            " WHERE " + USER_ID + " = ?";
    String INSERT_INTO_USER =
            "INSERT INTO " + TABLE_USER +
            " (" + USER_USERNAME + ", " + USER_PASSWORD + ", " + USER_EMAIL +
                    ", " + USER_FIRST_NAME + ", " + USER_LAST_NAME + ", " + USER_ROLE + ")" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    String SELECT_USERS_BY_ROLE =
            "SELECT " + USER_ID +  ", " + USER_USERNAME + ", " + USER_PASSWORD +
                    ", " + USER_EMAIL + ", " + USER_ROLE + ", " + USER_FIRST_NAME +
                    ", " + USER_LAST_NAME + ", " + USER_SPECIALITY_ID + ", " + SPECIALITY_NAME +
            " FROM " + TABLE_USER +
            " LEFT JOIN " + TABLE_SPECIALITY +
            " ON " + USER_SPECIALITY_ID + " = " + SPECIALITY_ID +
            " WHERE " + USER_ROLE + " = ?" +
            " LIMIT ?, ?";
    String UPDATE_USER =
            "UPDATE " + TABLE_USER + " SET " + USER_SPECIALITY_ID + " = ? WHERE " + USER_ID + " = ?";

    // Speciality
    String SELECT_SPECIALITIES_WITH_SUBJECTS =
            "SELECT " + SPECIALITY_ID + ", " + SPECIALITY_NAME + ", " + SUBJECT_ID + ", " + SUBJECT_NAME +
            " FROM " + TABLE_SPECIALITY +
            " LEFT JOIN " + TABLE_SPECIALITY_SUBJECT +
            " ON " + SPECIALITY_SUBJECT_SPECIALITY_ID + " = " + SPECIALITY_ID +
            " LEFT JOIN " + TABLE_SUBJECT +
            " ON " + SPECIALITY_SUBJECT_SUBJECT_ID + " = " + SUBJECT_ID;
    String SELECT_SPECIALITY_BY_ID =
            "SELECT * FROM " + TABLE_SPECIALITY + " WHERE " + SPECIALITY_ID + " = ?";

    // SubjectGrade
    String SELECT_USER_SUBJECT_GRADES =
            "SELECT " + SUBJECT_ID + ", " + SUBJECT_NAME + ", " + SUBJECT_GRADE_GRADE +
            " FROM " + TABLE_USER +
            " JOIN " + TABLE_SPECIALITY +
            " ON " + USER_SPECIALITY_ID + " = " + SPECIALITY_ID +
            " JOIN " + TABLE_SPECIALITY_SUBJECT +
            " ON " + SPECIALITY_SUBJECT_SPECIALITY_ID + " = " + SPECIALITY_ID +
            " LEFT JOIN " + TABLE_SUBJECT +
            " ON " + SPECIALITY_SUBJECT_SUBJECT_ID + " = " + SUBJECT_ID +
            " LEFT JOIN " + TABLE_SUBJECT_GRADE +
            " ON " + SUBJECT_GRADE_SUBJECT_ID + " = " + SUBJECT_ID + " AND " + SUBJECT_GRADE_USER_ID + " = " + USER_ID +
            " WHERE " + USER_ID + " = ?";
    String DELETE_FROM_SUBJECT_GRADE_BY_USER_ID =
            "DELETE FROM subject_grade WHERE subject_grade.user_id = ?";
    String INSERT_INTO_SUBJECT_GRADE =
            "INSERT INTO subject_grade (user_id, subject_id, grade) VALUES (?, ?, ?)";
    String SELECT_COUNT_USERS = "SELECT COUNT(*)\n" +
            "    FROM usr\n" +
            "    WHERE usr.role = 'USER'";
}
