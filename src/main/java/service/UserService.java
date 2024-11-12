package service;

import model.User;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Injecting the UserRepository to interact with the database for user-related operations
    @Autowired
    private UserRepository userRepository;

    // Create a new user in the database
    public User createUser(User user) {
        // Saves the user to the repository and returns the saved user object
        return userRepository.save(user);
    }

    // Get all users from the database
    public List<User> getAllUsers() {
        // Retrieves and returns a list of all users from the repository
        return userRepository.findAll();
    }

    // Get a specific user by their ID
    public User getUserById(Long id) {
        // Searches for the user by their ID, and throws an exception if not found
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update user details (username, password, email) for a specific user
    public User updateUser(Long id, User userDetails) {
        // Retrieves the existing user from the repository using the provided ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Updates the user's details only if the new values are not null
        if (userDetails.getUsername() != null) user.setUsername(userDetails.getUsername());
        if (userDetails.getPassword() != null) user.setPassword(userDetails.getPassword());
        if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());

        // Saves and returns the updated user object
        return userRepository.save(user);
    }

    // Delete a user by their ID
    public void deleteUser(Long id) {
        // Deletes the user from the repository using the provided ID
        userRepository.deleteById(id);
    }
}
