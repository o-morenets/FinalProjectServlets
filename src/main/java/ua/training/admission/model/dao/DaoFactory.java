package ua.training.admission.model.dao;

import ua.training.admission.controller.exception.AppException;
import ua.training.admission.view.Config;
import ua.training.admission.view.Messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * abstract class DaoFactory
 */
public abstract class DaoFactory {

    public abstract DaoConnection getConnection();

    public abstract UserDao createUserDao(DaoConnection connection);
    public abstract SpecialityDao createSpecialityDao(DaoConnection connection);
    public abstract SubjectDao createSubjectDao(DaoConnection connection);
    public abstract SubjectGradeDao createSubjectGradeDao(DaoConnection connection);

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(Config.DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(Config.DB_FACTORY_CLASS);
                instance = (DaoFactory) Class.forName(factoryClass).newInstance();
            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                throw new AppException(Messages.DAO_FACTORY_EXCEPTION, e);
            }
        }
        return instance;
    }
}
