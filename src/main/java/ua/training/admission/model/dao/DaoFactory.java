package ua.training.admission.model.dao;

import org.apache.log4j.Logger;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.config.AppConfig;
import ua.training.admission.view.Messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * abstract class DaoFactory
 *
 * @author Oleksii Morenets
 */
public abstract class DaoFactory {

    /** Returns DAO connection */
    public abstract DaoConnection getConnection();

    public abstract UserDao createUserDao(DaoConnection connection);

    public abstract RoleDao createRoleDao(DaoConnection connection);

    public abstract SpecialityDao createSpecialityDao(DaoConnection connection);

    public abstract SubjectGradeDao createSubjectGradeDao(DaoConnection connection);

    public abstract MessageDao createMessageDao(DaoConnection connection);

    /** DAO factory instance */
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
                        instance = (DaoFactory) Class.forName(factoryClass).newInstance();
                    } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                        log.error(Messages.DAO_FACTORY_EXCEPTION, e);
                        throw new AppException(Messages.DAO_FACTORY_EXCEPTION, e);
                    }
                }
            }
        }

        return instance;
    }
}
