package ua.training.admission.view;

import org.apache.log4j.Logger;
import ua.training.admission.config.AppConfig;
import ua.training.admission.controller.exception.AppException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * SQL holder for field names and sql queries
 *
 * @author Oleksii Morenets
 */
public interface SQL {

    /* SQL 'not unique' constraint code */
    int SQL_CONSTRAINT_NOT_UNIQUE = 1062;

    /* Logger */
    Logger log = Logger.getLogger(SQL.class);

    Properties sqlProperties = loadSqlProperties();

    /**
     * Load SQL queries from property file
     *
     * @return Properties object for SQL queries
     */
    static Properties loadSqlProperties() {
        try (InputStream inputStream = SQL.class.getResourceAsStream(AppConfig.SQL_PROPERTIES)) {
            Properties sqlProperties = new Properties();
            sqlProperties.load(inputStream);
            return sqlProperties;

        } catch (IOException e) {
            log.error(Messages.IO_EXCEPTION, e);
            throw new AppException(Messages.IO_EXCEPTION, e);
        }
    }

    static String getSqlElement(String key) {
        return sqlProperties.getProperty(key);
    }


    /* Fields */

    // User
    String USER_ID = "usr.id";
    String USER_USERNAME = "usr.username";
    String USER_PASSWORD = "usr.password";
    String USER_EMAIL = "usr.email";
    String USER_LAST_NAME = "usr.last_name";
    String USER_FIRST_NAME = "usr.first_name";
    String COUNT_ALL = "COUNT(*)";

    // User Role
    String USER_ROLE_ROLE_NAME = "user_role.role_name";

    // Speciality
    String SPECIALITY_ID = "speciality.id";
    String SPECIALITY_NAME = "speciality.name";

    // Subject
    String SUBJECT_ID ="subject.id";
    String SUBJECT_NAME = "subject.name";

    // SubjectGrade
    String SUBJECT_GRADE_GRADE = "subject_grade.grade";

    // Message
    String MESSAGE_AVERAGE_GRADE = "message.average_grade";
    String MESSAGE_ENTERED = "message.entered";
    String MESSAGE_MESSAGE_READ = "message.message_read";
    String MESSAGE_USER_ID = "message.user_id";


    /* SQL keys */

    // User
    String SELECT_USER_BY_USERNAME = "select.user.by.username";
    String SELECT_USER_BY_ID = "select.user.by.id";
    String INSERT_INTO_USER = "insert.into.user";
    String SELECT_USERS_BY_ROLE = "select.users.by.role";
    String UPDATE_USER = "update.user";
    String SELECT_COUNT_USERS_BY_ROLE = "select.count.users.by.role";

    // UserRole
    String INSERT_INTO_USER_ROLES = "insert.into.user_roles";

    // Speciality
    String SELECT_SPECIALITIES_WITH_SUBJECTS = "select.specialities.with.subjects";
    String SELECT_SPECIALITY_BY_ID = "select.speciality.by.id";

    // SubjectGrade
    String SELECT_USER_SUBJECT_GRADES = "select.user.subject.grades";
    String DELETE_FROM_SUBJECT_GRADE_BY_USER_ID_AND_SUBJECT_ID = "delete.from.subject_grade.by.user_id.and.subject_id";
    String INSERT_INTO_SUBJECT_GRADE = "insert.into.subject_grade";
    String SELECT_FROM_SUBJECT_GRADE_BY_USER_ID_AND_SUBJECT_ID = "select.from.subject_grade.by.user_id.and.subject_id";
    String UPDATE_SUBJECT_GRADE = "update.subject_grade";

    // Message
    String INSERT_INTO_MESSAGE = "insert.into.message";
    String UPDATE_MESSAGE = "update.message";
    String DELETE_MESSAGE = "delete.message";
}
