package ua.training.admission.model.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;

import static org.junit.Assert.*;

public class JdbcDaoFactoryTest {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getConnection() {
        DaoConnection connection = daoFactory.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void createUserDao() {
    }

    @Test
    public void createRoleDao() {
    }

    @Test
    public void createSpecialityDao() {
    }

    @Test
    public void createSubjectGradeDao() {
    }

    @Test
    public void createMessageDao() {
    }
}