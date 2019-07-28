package ua.training.admission.config;

import java.util.ResourceBundle;

/**
 * interface AdmissionConfig
 */
public interface AppConfig {

    ResourceBundle config = ResourceBundle.getBundle("i18n_config");

    String MESSAGES = config.getString("msg.bundle");

}
