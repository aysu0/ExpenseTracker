
package repository;

import model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Custom query methods

    // Find expenses by category
    List<Expense> findByCategory(String category);

    // Find expenses on a specific date
    List<Expense> findByDate(Date date);

    // Find expenses within a date range
    List<Expense> findByDateBetween(Date startDate, Date endDate);
    
    List<Expense> findByUserId(Long userId);
    
    List<Expense> findByCategoryAndUserId(String category, Long userId);


}

//package repository;
//
//import model.Expense;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.List;
//
//public interface ExpenseRepository extends JpaRepository<Expense, Long> {
//    List<Expense> findByUserId(Long userId); // Find expenses by user ID
//
//    Expense findByIdAndUserId(Long id, Long userId); // Find a specific expense for a user
//
//    void deleteByIdAndUserId(Long id, Long userId); // Delete a specific expense for a user
//}
