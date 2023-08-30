// SpringappApplicationTests.java
package com.example.springapp;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.springapp.controller.UserController;
import com.example.springapp.model.User;
import com.example.springapp.service.UserService;

public class SpringappApplicationTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_ValidInput_ReturnsUserWithId() {
        // Arrange
        User inputUser = new User("John Doe", "john@example.com", 30, "123 Main St");
        String uniqueId = UUID.randomUUID().toString();
        inputUser.setId(uniqueId);

        when(userService.createUser(any(User.class))).thenReturn(inputUser);

        // Act
        ResponseEntity<User> response = userController.createUser(inputUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Use assertAll to perform multiple assertions and handle null check
        assertAll("Response body assertions",
                () -> assertNotNull(response.getBody(), "Response body should not be null"),
                () -> assertEquals(uniqueId, response.getBody().getId())
        );
    }

    @Test
    public void testCreateUser_InvalidInput_ReturnsBadRequest() {
        // Arrange
        User inputUser = new User(null, "john@example.com", 30, "123 Main St");

        when(userService.createUser(any(User.class))).thenReturn(null);

        // Act
        ResponseEntity<User> response = userController.createUser(inputUser);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetUserById_ValidId_ReturnsUser() {
        // Arrange
        String userId = UUID.randomUUID().toString();
        User user = new User("Jane Smith", "jane@example.com", 25, "456 Elm St");
        user.setId(userId);

        when(userService.getUserById(userId)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.getUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userId, response.getBody().getId());
    }

    @Test
    public void testGetUserById_InvalidId_ReturnsNotFound() {
        // Arrange
        String invalidId = "invalid-id";

        when(userService.getUserById(invalidId)).thenReturn(null);

        // Act
        ResponseEntity<User> response = userController.getUser(invalidId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllUsers_ReturnsListOfUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User("John Doe", "john@example.com", 30, "123 Main St"));
        users.add(new User("Jane Smith", "jane@example.com", 25, "456 Elm St"));

        when(userService.getAllUsers()).thenReturn(users);

        // Act
        List<User> response = userController.getAllUsers();

        // Assert
        assertEquals(users.size(), response.size());
    }


    @Test 
    public void testcontrollerfolder() { 
        String directoryPath = "src/main/java/com/example/springapp/controller"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
    @Test 
    public void testcontrollerfile() { 
        String filePath = "src/main/java/com/example/springapp/controller/UserController.java"; 
        // Replace with the path to your file 
        File file = new File(filePath); 
        assertTrue(file.exists() && file.isFile()); 
    }

	@Test 
    public void testModelFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/model"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
    @Test 
    public void testModelFile() { 
        String filePath = "src/main/java/com/example/springapp/model/User.java"; 
        // Replace with the path to your file 
        File file = new File(filePath); 
        assertTrue(file.exists() && file.isFile()); 
    }

	

	@Test 
    public void testServiceFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/service"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
    @Test 
    public void testServieFile() { 
        String filePath = "src/main/java/com/example/springapp/service/UserService.java"; 
        // Replace with the path to your file 
        File file = new File(filePath); 
        assertTrue(file.exists() && file.isFile()); 
    }
}
