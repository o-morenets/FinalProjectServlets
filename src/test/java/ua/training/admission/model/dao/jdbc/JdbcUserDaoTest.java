package ua.training.admission.model.dao.jdbc;

import entityData.UsersData;
import org.junit.*;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;
import ua.training.admission.security.EncryptPassword;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFindById() {
        User expectedUser = testUsers.get("user");
        Optional<User> user = userDao.findById(expectedUser.getId());
        assertEquals(expectedUser, user.get());
    }

    @Test
    public void testGetByIdNotPreset() {
        assertFalse(userDao.findById(100500L).isPresent());
    }

    @Test
    public void testCreate() {
        String newUsername = "newUsername";
        String newPassword = "newPassword";
        String newEmail = "newEmail";
        String newFirstName = "newFirstName";
        String newLastName = "newLastName";

        User newUser = User.builder()
                .username(newUsername)
                .password(EncryptPassword.encrypt(newPassword))
                .email(newEmail)
                .firstName(newFirstName)
                .lastName(newLastName)
                .build();
        userDao.create(newUser);
        assertEquals(newUser, userDao.findByUsername(newUsername).get());
    }

    @Test
    public void testUpdate() {
        String username = "student";

        String newPassword = "newPassword";
        String newEmail = "newEmail";
        String newFirstName = "newFirstName";
        String newLastName = "newLastName";

        User expectedUser = testUsers.get(username);
        expectedUser.setPassword(EncryptPassword.encrypt(newPassword));
        expectedUser.setEmail(newEmail);
        expectedUser.setFirstName(newFirstName);
        expectedUser.setLastName(newLastName);

        User student = userDao.findByUsername(username).get();
        student.setPassword(EncryptPassword.encrypt(newPassword));
        student.setEmail(newEmail);
        student.setFirstName(newFirstName);
        student.setLastName(newLastName);

        userDao.update(student);
        assertEquals(expectedUser, userDao.findByUsername(username).get());
    }

    @Test
    public void testFindByUsername() {
        User expectedUser = testUsers.get("user");
        Optional<User> user = userDao.findByUsername(expectedUser.getUsername());
        assertEquals(expectedUser, user.get());
    }

    @Test
    public void testFindAllByRole() {
        List<User> expectedUserList = Arrays.stream(UsersData.values())
                .filter(usersData -> usersData.user.getRoles().contains(Role.USER))
                .map(usersData -> usersData.user)
                .collect(Collectors.toList());
        List<User> userList = userDao.findAllByRole(Role.USER, 1, 100500);
        assertEquals(expectedUserList, userList);
    }

    @Test
    public void getNumberOfRowsByRole() {
        long expectedNumberOfRowsByRole = Arrays.stream(UsersData.values())
                .filter(usersData -> usersData.user.getRoles().contains(Role.USER))
                .count();
        int numberOfRowsByRole = userDao.getNumberOfRowsByRole(Role.USER);
        assertEquals(expectedNumberOfRowsByRole, numberOfRowsByRole);
    }
}