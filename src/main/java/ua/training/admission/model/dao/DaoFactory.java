package ua.training.admission.model.dao;

import ua.training.admission.controller.exception.AppException;
import ua.training.admission.view.Errors;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * abstract class DaoFactory
 */
public abstract class DaoFactory {

    public abstract DaoConnection getConnection();

    public abstract UserDao createStaffDao(DaoConnection connection);

    private static final String DB_FILE = "/db.properties";

    private static final String DB_FACTORY_CLASS = "factory.class";

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                instance = (DaoFactory) Class.forName(factoryClass).newInstance();
            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                throw new AppException(Errors.DAO_FACTORY_EXCEPTION, e);
            }
        }
        return instance;
    }

}
