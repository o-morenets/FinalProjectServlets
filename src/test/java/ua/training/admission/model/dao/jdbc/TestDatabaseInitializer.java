package ua.training.admission.model.dao.jdbc;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

class TestDatabaseInitializer {
    private static final String DDL_CREATE_DB_SQL = "test_admission_servlets_create_db.sql";
    private static final String JDBC_DRIVER = "jdbc.driverClassName";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USER = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";

    private String ddlPopulate;

    TestDatabaseInitializer() {
        this(DDL_CREATE_DB_SQL);
    }

    private TestDatabaseInitializer(String ddlPopulateSql) {
        this.ddlPopulate = ddlPopulateSql;
    }

    void initTestJdbcDB() throws Exception {
        ResourceBundle jdbcProperties = ResourceBundle.getBundle("db");

        File script = new File(
                this.getClass()
                        .getClassLoader()
                        .getResource(ddlPopulate)
                        .getFile());

        String multiQuery = FileUtils.readFileToString(script, "utf-8");

        Class.forName(jdbcProperties.getString(JDBC_DRIVER));
        try (Connection con = DriverManager.getConnection(
                jdbcProperties.getString(JDBC_URL),
                jdbcProperties.getString(JDBC_USER),
                jdbcProperties.getString(JDBC_PASSWORD));
             Statement st = con.createStatement()) {

            st.execute(multiQuery);
        }
    }
}

