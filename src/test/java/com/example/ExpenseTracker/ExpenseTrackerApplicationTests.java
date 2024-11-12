package com.example.ExpenseTracker;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;
import service.UserService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
@Transactional
class ExpenseTrackerApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("john_doe");
        testUser.setEmail("john.doe@example.com");
        testUser.setPassword("password123");
    }

    // Test Create User (Create)
    @Test
    void testCreateUser() {
        User createdUser = userService.createUser(testUser);
        assertNotNull(createdUser);
        assertEquals("john_doe", createdUser.getUsername());
        assertEquals("john.doe@example.com", createdUser.getEmail());
    }

    // Test Get User by ID (Read)
    @Test
    void testGetUserById() {
        User createdUser = userService.createUser(testUser);
        assertNotNull(createdUser);

        User fetchedUser = userService.getUserById(createdUser.getId());
        assertNotNull(fetchedUser);
        assertEquals(createdUser.getId(), fetchedUser.getId());
        assertEquals("john_doe", fetchedUser.getUsername());
    }

    // Test Update User (Update)
    @Test
    void testUpdateUserProfile() {
        User createdUser = userService.createUser(testUser);
        assertNotNull(createdUser);

        createdUser.setUsername("john_updated");
        createdUser.setEmail("john.updated@example.com");

        userService.updateUser(createdUser.getId(), createdUser);

        User updatedUser = userRepository.findById(createdUser.getId()).orElseThrow();
        assertEquals("john_updated", updatedUser.getUsername());
        assertEquals("john.updated@example.com", updatedUser.getEmail());
    }

    // Test Delete User (Delete)
    @Test
    void testDeleteUser() {
        User createdUser = userService.createUser(testUser);
        assertNotNull(createdUser);

        Long userId = createdUser.getId();
        userService.deleteUser(userId);

        Optional<User> deletedUser = userRepository.findById(userId);
        assertTrue(deletedUser.isEmpty());
    }
}
