package service;

import model.Budget;
import model.User;
import repository.BudgetRepository;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import model.Expense;
import repository.ExpenseRepository;

@Service
public class BudgetService {

    // Injecting the BudgetRepository to handle CRUD operations for the Budget entity
    @Autowired
    private BudgetRepository budgetRepository;

    // Injecting the ExpenseRepository to handle operations related to the Expense entity
    @Autowired
    private ExpenseRepository expenseRepository;    

    // Creates and saves a new budget in the database
    public Budget createBudget(Budget budget) {
        // The save method persists the budget object to the database and returns the saved instance
        return budgetRepository.save(budget);
    }

    // Retrieves a budget by its ID, throws an exception if the budget is not found
    public Budget getBudget(Long id) {
        // Uses the repository to find the budget by its ID and throws an exception if not found
        return budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));
    }

    // Retrieves all budgets associated with a specific user by their userId
    public List<Budget> getAllBudgets(Long userId) {
        // Retrieves all budgets for the given user from the repository
        return budgetRepository.findByUserId(userId);
    }

    // Updates the details of an existing budget
    public Budget updateBudget(Long id, Budget budgetDetails) {
        // Retrieves the existing budget by its ID
        Budget budget = getBudget(id);
        
        // Updates the budget's fields with the new details provided in the argument
        budget.setAmount(budgetDetails.getAmount());
        budget.setCategory(budgetDetails.getCategory());
        
        // Saves and returns the updated budget
        return budgetRepository.save(budget);
    }    

    // Deletes a budget by its ID
    public void deleteBudget(Long id) {
        // Deletes the budget by its ID from the repository
        budgetRepository.deleteById(id);
    }

    // Checks if an expense exceeds the user's budget for a given category
    public boolean isOverBudget(Expense expense) {
        // Retrieves the budget for the category and user associated with the given expense
        Budget budget = budgetRepository.findByCategoryAndUserId(expense.getCategory(), expense.getUser().getId());
        
        // If the budget exists, check whether the total expenses for the category, including the current expense, exceed the budget
        if (budget != null) {
            // Calculates the total expenses in the specified category for the user
            double totalExpenses = expenseRepository.findByCategoryAndUserId(expense.getCategory(), expense.getUser().getId())
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
            
            // Returns true if the total expenses exceed the budget limit
            return totalExpenses + expense.getAmount() > budget.getAmount();
        }
        
        // If no budget is found for the category, it returns false (not over budget)
        return false;
    }   

    // Retrieves a specific budget based on userId, category, month, and year
    public Budget getBudgetForUserAndCategoryAndMonthAndYear(Long userId, String category, int month, int year) {
        // Uses the repository to find the budget based on multiple parameters
        return budgetRepository.findByUserIdAndCategoryAndMonthAndYear(userId, category, month, year);
    }
}
