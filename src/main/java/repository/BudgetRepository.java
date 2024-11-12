package repository;

import model.Budget;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// This interface defines the repository for the 'Budget' entity, extending JpaRepository for CRUD operations.
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    
    // Finds all budgets associated with a specific user (using the user ID).
    List<Budget> findByUserId(Long userId);
    
    // Finds a budget based on the category and user ID.
    // It returns a single Budget object.
    Budget findByCategoryAndUserId(String category, Long userId);
    
    // Finds an optional budget based on the user ID and category.
    // If no budget is found, it returns an empty Optional.
    Optional<Budget> findByUserIdAndCategory(Long userId, String category);

    // Finds a budget based on a specific user, category, month, and year.
    // This is useful for finding a budget for a particular time period (month/year) for a specific user and category.
    Budget findByUserAndCategoryAndMonthAndYear(User user, String category, Integer month, Integer year);
    
    // Finds a budget based on user ID, category, month, and year.
    // This is an alternative to the previous method, where instead of a User object, the user ID is provided directly.
    Budget findByUserIdAndCategoryAndMonthAndYear(Long userId, String category, Integer month, Integer year);
}
