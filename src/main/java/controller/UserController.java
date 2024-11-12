package controller;

import model.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Marks this class as a REST controller to handle HTTP requests
@RequestMapping("/users")  // Base URL for all routes in this controller. All routes will start with "/users"
public class UserController {

    @Autowired  // Automatically injects the UserService bean into this controller
    private UserService userService;

    // POST method to create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Delegate to the service layer to create a new user and return the created user
        return userService.createUser(user);
    }

    // GET method to retrieve all users
    @GetMapping
    public List<User> getAllUsers() {
        // Call the service to get all users and return the list of users
        return userService.getAllUsers();
    }

    // GET method to retrieve a specific user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        // Call the service to get a user by the given ID and return the user object
        return userService.getUserById(id);
    }

    // PUT method to update user details by their ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        // Call the service to update the user with the given ID and return the updated user
        return userService.updateUser(id, userDetails);
    }

    // DELETE method to remove a user by their ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Call the service to delete the user with the given ID
        userService.deleteUser(id);
    }
}
