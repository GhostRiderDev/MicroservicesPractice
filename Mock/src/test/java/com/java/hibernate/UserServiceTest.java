package com.java.hibernate;

import com.java.hibernate.DAO.UserDAO;
import com.java.hibernate.service.UserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

public class UserServiceTest extends TestCase {
    @Test
    public void testGetNameById() {
        UserDAO mockDAO = PowerMockito.mock(UserDAO.class);
        PowerMockito.when(mockDAO.findNameById(111)).thenReturn("cos4h");
        UserService userService = new UserService(mockDAO);
        String name = userService.getNameById(111);
        assertEquals("cos4h", name);
    }

    @Test
    public void testGetEmailById() {
        UserDAO mockDAO = PowerMockito.mock(UserDAO.class);
        PowerMockito.when(mockDAO.findEmailById(1111)).thenReturn("cos4h@gmail.com");
        UserService userService = new UserService(mockDAO);
        String email = userService.getEmailById(1111);
        assertEquals("olvadis2004@gmail.com", email);
    }
}
