package ua.training.admission.model.dao.jdbc;

import entityData.UserData;
import org.junit.*;
import ua.training.admission.controller.exception.AppException;
import ua.training.admission.model.dao.DaoConnection;
import ua.training.admission.model.dao.DaoFactory;
import ua.training.admission.model.dao.UserDao;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.User;
import ua.training.admission.security.EncryptPassword;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class JdbcUserDaoTest {

    private Map<String, User> testUsers = new HashMap<>();

    {
        for (UserData userData : UserData.values()) {
            testUsers.put(userData.user.getUsername(), userData.user);
        }
    }

    private UserDao userDao;

    @BeforeClass
    public static void initTestDataBase() throws Exception {
//        new TestDatabaseInitializer().initTestJdbcDB();
    }

    @Before
    public void setUp() throws Exception {
        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoConnection connection = daoFactory.getConnection();
        userDao = daoFactory.createUserDao(connection);
    }

    @Test
    public void testFindById() {
        User expectedUser = testUsers.get("user");
        Optional<User> user = userDao.findById(expectedUser.getId());

        assertTrue(user.isPresent());
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

        assertNotNull(newUser.getId());
    }

    @Test(expected = AppException.class)
    public void testCreateDuplicate() {
        String duplicateUsername = "user";
        String newPassword = "newPassword";
        String newEmail = "newEmail";
        String newFirstName = "newFirstName";
        String newLastName = "newLastName";

        User newUser = User.builder()
                .username(duplicateUsername)
                .password(EncryptPassword.encrypt(newPassword))
                .email(newEmail)
                .firstName(newFirstName)
                .lastName(newLastName)
                .build();
        userDao.create(newUser);
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

        Optional<User> user = userDao.findByUsername(username);

        assertTrue(user.isPresent());

        User student = user.get();
        student.setPassword(EncryptPassword.encrypt(newPassword));
        student.setEmail(newEmail);
        student.setFirstName(newFirstName);
        student.setLastName(newLastName);

        userDao.update(student);

        assertEquals(expectedUser, user.get());
    }

    @Test
    public void testFindByUsername() {
        User expectedUser = testUsers.get("user");
        Optional<User> user = userDao.findByUsername(expectedUser.getUsername());

        assertTrue(user.isPresent());
        assertEquals(expectedUser, user.get());
    }

    @Test
    public void testFindAllByRole() {
        List<User> expectedUserList = Arrays.stream(UserData.values())
                .filter(userData -> userData.user.getRoles().contains(Role.USER))
                .map(userData -> userData.user)
                .collect(Collectors.toList());
        List<User> userList = userDao.findAllByRole(Role.USER, 1, 100500);

        assertEquals(expectedUserList, userList);
    }

    @Test
    public void getNumberOfRowsByRole() {
        long expectedNumberOfRowsByRole = Arrays.stream(UserData.values())
                .filter(userData -> userData.user.getRoles().contains(Role.USER))
                .count();
        int numberOfRowsByRole = userDao.getNumberOfRowsByRole(Role.USER);

        assertEquals(expectedNumberOfRowsByRole, numberOfRowsByRole);
    }
}