
package service;

import model.Expense;
import model.User;
import repository.ExpenseRepository;
import repository.UserRepository;
import repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import model.Budget;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BudgetService budgetService;
    
    @Autowired
    private BudgetRepository budgetRepository;
   
    
//    public Expense createExpense(Expense expense, String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        expense.setUser(user); // Set the user to the budget
//        return expenseRepository.save(expense);
//    }
    
//////    public Expense createExpense(Expense expense) {
//////        User user = expense.getUser();
//////        if (user != null && user.getId() != null) {
//////            if (budgetService.isOverBudget(expense)) {
//////                throw new RuntimeException("Oops, you went over your budget in category: " + expense.getCategory());
//////            }
//////            return expenseRepository.save(expense);
//////        } else {
//////            throw new RuntimeException("User not found.");
//////        }
//////    }
////    
//    
    
        public Expense createExpense(Expense expense) {
        User user = expense.getUser();

        // Ensure the user exists
        if (user != null && user.getId() != null) {

            // Check if a budget exists for this user's specified category
            Optional<Budget> optionalBudget = budgetRepository.findByUserIdAndCategory(user.getId(), expense.getCategory());

            if (optionalBudget.isEmpty()) {
                throw new RuntimeException("No budget found for category '" + expense.getCategory() + "'. Please create a budget first.");
            }

            // If a budget exists, check if the expense would exceed the budget
            if (budgetService.isOverBudget(expense)) {
                throw new RuntimeException("Oops, you went over your budget in category: " + expense.getCategory());
            }

            // Save the expense if all checks pass
            return expenseRepository.save(expense);

        } else {
            throw new RuntimeException("User not found.");
        }
    }
    
    
    
    public Expense getExpense(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public List<Expense> getAllExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

//    public List<Expense> getAllExpensesForUser(String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        
//        return expenseRepository.findByUserId(user.getId());
//    }
    
//    public Expense createExpense(Expense expense) {
//        return expenseRepository.save(expense);
//    }

//    public List<Expense> getAllExpenses() {
//        return expenseRepository.findAll();
//    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = getExpense(id);
        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDescription(expenseDetails.getDescription());
        expense.setDate(expenseDetails.getDate());
        
        return expenseRepository.save(expense);
    }
    

    
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}


//package service;
//
//import model.Expense;
//import model.User;
//import repository.ExpenseRepository;
//import repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ExpenseService {
//
//    @Autowired
//    private ExpenseRepository expenseRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Create a new Expense
//    public Expense createExpense(Expense expense) {
//        return expenseRepository.save(expense);
//    }
//
//    // Get all Expenses for a specific User
//    public List<Expense> getAllExpensesByUser(Long userId) {
//        return expenseRepository.findByUserId(userId);
//    }
//
//    // Get Expense by ID and User
//    public Expense getExpenseByIdAndUser(Long expenseId, Long userId) {
//        return expenseRepository.findByIdAndUserId(expenseId, userId);
//    }
//
//    // Update an Expense for a specific User
//    public Expense updateExpense(Long expenseId, Expense expenseDetails, Long userId) {
//        Optional<Expense> expense = expenseRepository.findById(expenseId);
//        if (expense.isPresent()) {
//            Expense existingExpense = expense.get();
//            existingExpense.setDescription(expenseDetails.getDescription());
//            existingExpense.setAmount(expenseDetails.getAmount());
//            existingExpense.setUser(userRepository.findById(userId).orElse(null));  // Ensure the user exists
//            return expenseRepository.save(existingExpense);
//        } else {
//            return null; // Handle this appropriately in your code
//        }
//    }
//
//    // Delete an Expense for a specific User
//    public void deleteExpense(Long expenseId, Long userId) {
//        expenseRepository.deleteByIdAndUserId(expenseId, userId);
//    }
//}
