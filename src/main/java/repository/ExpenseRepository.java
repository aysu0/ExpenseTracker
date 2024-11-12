package repository;

import model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// This interface defines the repository for the 'Expense' entity, extending JpaRepository for CRUD operations.
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Custom query methods

    // Finds a list of expenses based on the category.
    // Returns a list of 'Expense' objects that match the given category.
    List<Expense> findByCategory(String category);

    // Finds all expenses associated with a specific user ID.
    // Returns a list of 'Expense' objects that belong to the user with the specified userId.
    List<Expense> findByUserId(Long userId);

    // Finds all expenses based on the category and user ID.
    // Returns a list of 'Expense' objects that belong to the specified user and have the given category.
    List<Expense> findByCategoryAndUserId(String category, Long userId);

    // A custom query to calculate the total amount of expenses for a specific user, category, month, and year.
    // The 'COALESCE' function ensures that if no expenses are found, it returns 0 instead of null.
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user = :user AND e.category = :category AND e.month = :month AND e.year = :year")
    // The 'user', 'category', 'month', and 'year' parameters are passed using the @Param annotation to bind method arguments to query parameters.
    Double findTotalAmountByUserAndCategoryAndMonthAndYear(@Param("user") User user, 
                                                           @Param("category") String category,
                                                           @Param("month") Integer month,
                                                           @Param("year") Integer year);

}
