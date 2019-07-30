package ua.training.admission.config;

import java.util.ResourceBundle;

/**
 * interface AppConfig
 */
public interface AppConfig {

    String DB_FILE = "/db/db.properties";
    String DB_FACTORY_CLASS = "factory.class";
    String JAVA_COMP_ENV_JDBC = "java:comp/env/jdbc/admission_servlets";

    ResourceBundle config = ResourceBundle.getBundle("i18n/i18n_config");
    String MESSAGES = config.getString("msg.bundle");
}
