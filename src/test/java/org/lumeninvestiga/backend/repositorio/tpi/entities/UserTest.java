package org.lumeninvestiga.backend.repositorio.tpi.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Role;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.UserDetail;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.StorableItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        UserDetail userDetail = new UserDetail();
        userDetail.setName("John");
        userDetail.setLastName("Doe");
        userDetail.setEmailAddress("john.doe@example.com");

        user = new User();
        user.setUserDetail(userDetail);
        user.setUsername("johndoe");
        user.setPassword("password");
        user.setRole(Role.STUDENT);

        List<StorableItem> storableItems = new ArrayList<>();
        user.setStorableItems(storableItems);
    }

    @Test
    public void testUserDetail() {
        assertEquals("John", user.getUserDetail().getName());
        assertEquals("Doe", user.getUserDetail().getLastName());
        assertEquals("john.doe@example.com", user.getUserDetail().getEmailAddress());
    }

    @Test
    public void testAddStorableItem() {
        StorableItem item = new StorableItem();
        user.addStorableItem(item);
        assertTrue(user.getStorableItems().contains(item));
    }

    @Test
    public void testRemoveStorableItem() {
        StorableItem item = new StorableItem();
        user.addStorableItem(item);
        user.removeStorableItem(item);
        assertTrue(!user.getStorableItems().contains(item));
    }
}
