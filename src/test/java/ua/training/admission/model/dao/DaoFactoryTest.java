package ua.training.admission.model.dao;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DaoFactoryTest {

    @Test
    public void testGetInstance() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        assertNotNull(daoFactory);
    }
}