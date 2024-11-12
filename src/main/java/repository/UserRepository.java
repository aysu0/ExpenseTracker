package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// This interface defines the repository for the 'User' entity, extending JpaRepository for CRUD operations.
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query method to find a user by their username.
    // It returns an Optional<User>, which may contain a user or be empty if no user with the given username is found.
    Optional<User> findByUsername(String username);
}
