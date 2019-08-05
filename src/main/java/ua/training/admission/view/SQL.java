package ua.training.admission.view;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SQL {

    /* SQL 'not unique' constraint code */
    public static final int SQL_CONSTRAINT_NOT_UNIQUE = 1062;

    /* Logger */
    private static final Logger log = Logger.getLogger(SQL.class);

    private static Properties sqlProperties = loadSqlProperties();

    private static Properties loadSqlProperties() {
        try (InputStream inputStream = SQL.class.getResourceAsStream("/db/sql.properties")) {
            Properties sqlProperties = new Properties();
            sqlProperties.load(inputStream);
            return sqlProperties;

        } catch (IOException e) {
            log.error(Messages.IO_EXCEPTION, e);
            throw new AppException(Messages.IO_EXCEPTION, e);
        }
    }

    public static String getSqlElement(String key) {
        return sqlProperties.getProperty(key);
    }


    /* Fields */

    // User
    public static final String USER_ID = "usr.id";
    public static final String USER_USERNAME = "usr.username";
    public static final String USER_PASSWORD = "usr.password";
    public static final String USER_EMAIL = "usr.email";
    public static final String USER_LAST_NAME = "usr.last_name";
    public static final String USER_FIRST_NAME = "usr.first_name";
    public static final String USER_SPECIALITY_ID = "usr.speciality_id";
    public static final String COUNT_ALL = "COUNT(*)";

    // User Role
    public static final String USER_ROLE_ROLE_NAME = "user_role.role_name";

    // Speciality
    public static final String SPECIALITY_ID = "speciality.id";
    public static final String SPECIALITY_NAME = "speciality.name";

    // Subject
    public static final String SUBJECT_ID ="subject.id";
    public static final String SUBJECT_NAME = "subject.name";

    // SubjectGrade
    public static final String SUBJECT_GRADE_GRADE = "subject_grade.grade";


    /* SQL keys */

    // User
    public static final String SELECT_USER_BY_USERNAME = "select.user.by.username";
    public static final String SELECT_USER_BY_ID = "select.user.by.id";
    public static final String INSERT_INTO_USER = "insert.into.user";
    public static final String SELECT_USERS_BY_ROLE = "select.users.by.role";
    public static final String UPDATE_USER = "update.user";
    public static final String SELECT_COUNT_USERS_BY_ROLE = "select.count.users.by.role";

    // User_Role
    public static final String INSERT_INTO_USER_ROLES = "insert.into.user_roles";

    // Speciality
    public static final String SELECT_SPECIALITIES_WITH_SUBJECTS = "select.specialities.with.subjects";
    public static final String SELECT_SPECIALITY_BY_ID = "select.speciality.by.id";

    // SubjectGrade
    public static final String SELECT_USER_SUBJECT_GRADES = "select.user.subject.grades";
    public static final String DELETE_FROM_SUBJECT_GRADE_BY_USER_ID_AND_SUBJECT_ID = "delete.from.subject_grade.by.user_id.and.subject_id";
    public static final String INSERT_INTO_SUBJECT_GRADE = "insert.into.subject_grade";
    public static final String SELECT_FROM_SUBJECT_GRADE_BY_USER_ID_AND_SUBJECT_ID = "select.from.subject_grade.by.user_id.and.subject_id";
    public static final String UPDATE_SUBJECT_GRADE = "update.subject_grade";
}
