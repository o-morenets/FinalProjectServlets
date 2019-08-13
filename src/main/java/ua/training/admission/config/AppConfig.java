package ua.training.admission.config;

import java.util.ResourceBundle;

/**
 * Application configuration
 *
 * @author Oleksii Morenets
 */
public interface AppConfig {

    String DB_FILE = "/db/db.properties";
    String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
    String JDBC_URL = "jdbc.url";
    String JDBC_USERNAME = "jdbc.username";
    String JDBC_PASSWORD = "jdbc.password";

    String DB_FACTORY_CLASS = "factory.class";
    String SQL_PROPERTIES = "/db/sql.properties";
    String JAVA_COMP_ENV_JDBC = "java:comp/env/jdbc/admission_servlets";

    ResourceBundle config = ResourceBundle.getBundle("i18n/i18n_config");
    String MESSAGES = config.getString("msg.bundle");
}
