package ua.training.admission.model.dao.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import ua.training.admission.config.AppConfig;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.view.Messages;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ua.training.admission.config.AppConfig.*;

public class ConnectionPoolHolder {

    /* Logger */
    private static final Logger log = Logger.getLogger(ConnectionPoolHolder.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    InputStream inputStream = DaoFactory.class.getResourceAsStream(AppConfig.DB_FILE);
                    Properties dbProps = new Properties();
                    try {
                        dbProps.load(inputStream);
                        BasicDataSource basicDataSource = new BasicDataSource();

                        basicDataSource.setDriverClassName(dbProps.getProperty(JDBC_DRIVER_CLASS_NAME));
                        basicDataSource.setUrl(dbProps.getProperty(JDBC_URL));
                        basicDataSource.setUsername(dbProps.getProperty(JDBC_USERNAME));
                        basicDataSource.setPassword(dbProps.getProperty(JDBC_PASSWORD));

                        dataSource = basicDataSource;

                    } catch (IOException e) {
                        log.error(Messages.CONNECTION_POOL_EXCEPTION, e);
                    }
                }
            }
        }
        return dataSource;
    }
}
