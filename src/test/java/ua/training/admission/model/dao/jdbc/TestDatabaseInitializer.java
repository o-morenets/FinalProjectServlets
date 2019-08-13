package ua.training.admission.model.dao.jdbc;

import org.apache.commons.io.FileUtils;
import ua.training.admission.config.AppConfig;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

class TestDatabaseInitializer {
    private static final String DDL_CREATE_DB_SQL = "db/test_admission_servlets_create_db.sql";
    private static final String JDBC_DRIVER = "jdbc.driverClassName";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USER = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";

    private String ddlCreateDbSql;

    TestDatabaseInitializer() {
        this(DDL_CREATE_DB_SQL);
    }

    private TestDatabaseInitializer(String ddlCreateDbSql) {
        this.ddlCreateDbSql = ddlCreateDbSql;
    }

    void initTestJdbcDB() throws Exception {
        InputStream inputStream = TestDatabaseInitializer.class.getResourceAsStream(AppConfig.DB_FILE);
        Properties dbProps = new Properties();
        dbProps.load(inputStream);

        File script = new File(
                getClass()
                        .getClassLoader()
                        .getResource(ddlCreateDbSql)
                        .getFile());

        String multiQuery = FileUtils.readFileToString(script, "UTF-8");

        Class.forName(dbProps.getProperty(JDBC_DRIVER));
        try (Connection connection = DriverManager.getConnection(
                dbProps.getProperty(JDBC_URL),
                dbProps.getProperty(JDBC_USER),
                dbProps.getProperty(JDBC_PASSWORD));
             Statement st = connection.createStatement()) {

            st.execute(multiQuery);
        }
    }
}

