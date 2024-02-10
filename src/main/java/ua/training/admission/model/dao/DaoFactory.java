package ua.training.admission.model.dao;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.config.AppConfig;
import ua.training.admission.view.Messages;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * abstract class DaoFactory
 *
 * @author Oleksii Morenets
 */
public abstract class DaoFactory {

    /**
     * Returns DAO connection
     */
    public abstract DaoConnection getConnection();

    /**
     * Creates User DAO
     *
     * @param connection database connection
     * @return implementation of User DAO
     */
    public abstract UserDao createUserDao(DaoConnection connection);

    /**
     * Creates Role DAO
     *
     * @param connection database connection
     * @return implementation of Role DAO
     */
    public abstract RoleDao createRoleDao(DaoConnection connection);

    /**
     * Creates Speciality DAO
     *
     * @param connection database connection
     * @return implementation of Speciality DAO
     */
    public abstract SpecialityDao createSpecialityDao(DaoConnection connection);

    /**
     * Creates SubjectGrade DAO
     *
     * @param connection database connection
     * @return implementation of SubjectGrade DAO
     */
    public abstract SubjectGradeDao createSubjectGradeDao(DaoConnection connection);

    /**
     * Creates Message DAO
     *
     * @param connection database connection
     * @return implementation of Message DAO
     */
    public abstract MessageDao createMessageDao(DaoConnection connection);

    /**
     * DAO factory instance
     */
    private static DaoFactory instance;

    /* Logger */
    private static final Logger log = Logger.getLogger(DaoFactory.class);

    /**
     * Get an instance of DAO factory
     *
     * @return singleton instance of DAO factory
     */
    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    try {
                        InputStream inputStream = DaoFactory.class.getResourceAsStream(AppConfig.DB_FILE);
                        Properties dbProps = new Properties();
                        dbProps.load(inputStream);
                        String factoryClass = dbProps.getProperty(AppConfig.DB_FACTORY_CLASS);
                        instance = (DaoFactory) Class.forName(factoryClass).getDeclaredConstructor().newInstance();

                    } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException |
							 NoSuchMethodException | InvocationTargetException e) {
                        log.error(Messages.DAO_FACTORY_EXCEPTION, e);
                        throw new AppException(Messages.DAO_FACTORY_EXCEPTION, e);
                    }
                }
            }
        }

        return instance;
    }
}
