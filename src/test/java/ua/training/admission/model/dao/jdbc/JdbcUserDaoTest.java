package ua.training.admission.model.dao.jdbc;

import entityData.UsersData;
import org.junit.*;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.User;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class JdbcUserDaoTest {

    private Map<String, User> testUsers = new HashMap<>();

    {
        for (UsersData usersData : UsersData.values()) {
            testUsers.put(usersData.user.getUsername(), usersData.user);
        }
    }

    private DaoConnection connection;
    private UserDao userDao;

    @BeforeClass
    public static void initTestDataBase() throws Exception {
//        new TestDatabaseInitializer().initTestJdbcDB();
    }

    @Before
    public void setUp() throws Exception {
        DaoFactory daoFactory = DaoFactory.getInstance();
        connection = daoFactory.getConnection();
        userDao = daoFactory.createUserDao(connection);
        connection.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        connection.commit();
    }

    @Test
    public void testFindById() {
        User testUser = testUsers.get("user");
        Optional<User> user = userDao.findById(testUser.getId());
        assertEquals(testUser, user.get());
    }

    @Test
    public void findAll() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findAllByRole() {
    }

    @Test
    public void getNumberOfRowsByRole() {
    }
}