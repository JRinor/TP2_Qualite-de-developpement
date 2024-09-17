package com.example.tpapi.test;

import com.example.tpapi.controller.UserController;
import com.example.tpapi.model.User;
import com.example.tpapi.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User("Elon", "Musk");
        User user2 = new User("Jeff", "Bezos");
        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userController.getAllUsers();

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getFirstName(), "Elon");
        assertEquals(result.get(1).getFirstName(), "Jeff");
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById_ExistingUser() {
        User user = new User("Bill", "Gates");
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getFirstName(), "Bill");
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserById_NonExistingUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(response.getBody());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUser() {
        User user = new User("Mark", "Zuckerberg");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userController.createUser(user);

        assertNotNull(result);
        assertEquals(result.getFirstName(), "Mark");
        assertEquals(result.getLastName(), "Zuckerberg");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_ExistingUser() {
        User existingUser = new User("Warren", "Buffett");
        existingUser.setId(1L);
        User updatedUser = new User("Warren", "Buffett Jr.");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getLastName(), "Buffett Jr.");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_NonExistingUser() {
        User updatedUser = new User("Larry", "Ellison");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(response.getBody());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testDeleteUser_ExistingUser() {
        User user = new User("Steve", "Ballmer");
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUser_NonExistingUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).delete(any(User.class));
    }
}
