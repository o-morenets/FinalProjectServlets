package ua.training.admission.model.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberValidatorTest {

    private NumberValidator numberValidator;

    @Before
    public void setUp() throws Exception {
        numberValidator = new NumberValidator();
    }

    @Test
    public void testIsValidDouble_Valid() {
        String number = "50.94";

        assertTrue(numberValidator.isValidDouble(number));
    }

    @Test
    public void testIsValidDouble_ValidMin() {
        String number = "0.00";

        assertTrue(numberValidator.isValidDouble(number));
    }

    @Test
    public void testIsValidDouble_ValidMax() {
        String number = "100.00";

        assertTrue(numberValidator.isValidDouble(number));
    }

    @Test
    public void testIsValidDouble_NotNumber() {
        String notNumber = "35o";

        assertFalse(numberValidator.isValidDouble(notNumber));
    }

    @Test
    public void testIsValidDouble_LessThanMin() {
        String number = "-0.000001";

        assertFalse(numberValidator.isValidDouble(number));
    }

    @Test
    public void testIsValidDouble_GreaterThanMax() {
        String number = "100.0000001";

        assertFalse(numberValidator.isValidDouble(number));
    }
}