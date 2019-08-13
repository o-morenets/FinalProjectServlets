package ua.training.admission.model.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import ua.training.admission.model.dao.*;

import static org.junit.Assert.assertNotNull;

public class JdbcDaoFactoryTest {

    private DaoFactory daoFactory;

    @Before
    public void setUp() throws Exception {
        daoFactory = DaoFactory.getInstance();
    }

    @Test
    public void getConnection() {
        DaoConnection connection = daoFactory.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testCreateUserDao() {
        DaoConnection connection = daoFactory.getConnection();
        UserDao userDao = daoFactory.createUserDao(connection);
        assertNotNull(userDao);
    }

    @Test
    public void testCreateRoleDao() {
        DaoConnection connection = daoFactory.getConnection();
        RoleDao roleDao = daoFactory.createRoleDao(connection);
        assertNotNull(roleDao);
    }

    @Test
    public void testCreateSpecialityDao() {
        DaoConnection connection = daoFactory.getConnection();
        SpecialityDao specialityDao = daoFactory.createSpecialityDao(connection);
        assertNotNull(specialityDao);
    }

    @Test
    public void testCreateSubjectGradeDao() {
        DaoConnection connection = daoFactory.getConnection();
        SubjectGradeDao subjectGradeDao = daoFactory.createSubjectGradeDao(connection);
        assertNotNull(subjectGradeDao);
    }

    @Test
    public void testCreateMessageDao() {
        DaoConnection connection = daoFactory.getConnection();
        MessageDao messageDao = daoFactory.createMessageDao(connection);
        assertNotNull(messageDao);
    }
}