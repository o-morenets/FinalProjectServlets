package ua.training.admission.model.service;

import entityData.SpecialityData;
import entityData.UserData;
import org.junit.Before;
import org.junit.Test;
import ua.training.admission.model.entity.Role;
import ua.training.admission.model.entity.Speciality;
import ua.training.admission.model.entity.User;
import ua.training.admission.security.EncryptPassword;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UserServiceTest {

    private Map<String, User> testUsers = new HashMap<>();

    {
        for (UserData userData : UserData.values()) {
            testUsers.put(userData.user.getUsername(), userData.user);
        }
    }

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = UserService.getInstance();
    }

    @Test
    public void testGetInstance() {
        userService = UserService.getInstance();
        assertNotNull(userService);
    }

    @Test
    public void testLogin_success() {
        String username = "user";
        String password = "user";
        Optional<User> user = userService.login(username, EncryptPassword.encrypt(password));
        User expectedUser = testUsers.get(username);
        assertTrue(user.isPresent());
        assertEquals(expectedUser, user.get());
    }

    @Test
    public void testLogin_badCredentials() {
        String username = "user";
        String password = "incorrectPassword";
        Optional<User> user = userService.login(username, EncryptPassword.encrypt(password));
        assertFalse(user.isPresent());
    }

    @Test
    public void testFindById() {
        User expectedUser = testUsers.get("user");
        Long id = expectedUser.getId();
        Optional<User> user = userService.findById(id);

        assertTrue(user.isPresent());
        assertEquals(expectedUser, user.get());
    }

    @Test
    public void create() {
    }

    @Test
    public void testFindAllByRole() {
        List<User> expectedUserList = Arrays.stream(UserData.values())
                .filter(userData -> userData.user.getRoles().contains(Role.USER))
                .map(userData -> userData.user)
                .collect(Collectors.toList());
        List<User> userList = userService.findAllByRole(Role.USER, 1, 100500);

        assertEquals(expectedUserList, userList);
    }

    @Test
    public void testGetNumberOfRowsByRole() {
        long expectedNumberOfRowsByRole = Arrays.stream(UserData.values())
                .filter(userData -> userData.user.getRoles().contains(Role.USER))
                .count();
        int numberOfRowsByRole = userService.getNumberOfRowsByRole(Role.USER);

        assertEquals(expectedNumberOfRowsByRole, numberOfRowsByRole);
    }

    @Test
    public void testUpdateSpeciality() {
        String username = "student";
        User expectedUser = testUsers.get(username);
        expectedUser.setSpeciality(SpecialityData.SP_5.speciality);

        userService.updateSpeciality(expectedUser.getId(), expectedUser.getSpeciality().getId());
        Optional<User> user = userService.findById(expectedUser.getId());
        assertTrue(user.isPresent());
        assertEquals(expectedUser, user.get());
    }

    @Test
    public void testSendMessages() {
        // TODO
    }
}