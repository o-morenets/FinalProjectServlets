package ua.training.admission.model.validator;

import org.junit.Before;
import org.junit.Test;
import ua.training.admission.model.entity.User;

import static org.junit.Assert.*;

public class UserValidatorTest {

    private UserValidator userValidator;

    @Before
    public void setUp() throws Exception {
        userValidator = new UserValidator();
    }

    @Test
    public void testValidate_valid() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("email@domain.com")
                .firstName("firstName")
                .lastName("lastName")
                .build();
        userValidator.validate(user);

        assertFalse(userValidator.hasErrors());
    }

    @Test
    public void testValidate_invalidUsername() {
        User user = User.builder()
                .username("")
                .password("password")
                .email("email@domain.com")
                .firstName("firstName")
                .lastName("lastName")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }

    @Test
    public void testValidate_invalidPassword() {
        User user = User.builder()
                .username("username")
                .password("")
                .email("email@domain.com")
                .firstName("firstName")
                .lastName("lastName")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }

    @Test
    public void testValidate_invalidEmailEmpty() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("")
                .firstName("firstName")
                .lastName("lastName")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }

    @Test
    public void testValidate_invalidEmailIncorrect() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("emaildomaincom")
                .firstName("firstName")
                .lastName("lastName")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }

    @Test
    public void testValidate_invalidFirstName() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("email@domain.com")
                .firstName("")
                .lastName("lastName")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }

    @Test
    public void testValidate_invalidLastName() {
        User user = User.builder()
                .username("username")
                .password("password")
                .email("email@domain.com")
                .firstName("firstName")
                .lastName("")
                .build();
        userValidator.validate(user);

        assertTrue(userValidator.hasErrors());
    }
}