package service;

import DTO.BudgetRequest;
import model.Budget;
import model.User;
import repository.BudgetRepository;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import model.Expense;
import repository.ExpenseRepository;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ExpenseRepository expenseRepository;    
    
//    @Autowired
//    private UserRepository userRepository;

//    public Budget createBudget(Budget budget, String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        budget.setUser(user); // Set the user to the budget
//        return budgetRepository.save(budget);
//    }

//    public Budget createBudget(BudgetRequest budgetRequest) {
//        // Fetch the user by username
//        User user = userRepository.findByUsername(budgetRequest.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Create the Budget object and set its fields
//        Budget budget = new Budget();
//        budget.setAmount(budgetRequest.getAmount());
//        budget.setCategory(budgetRequest.getCategory());
//        budget.setMonth(budgetRequest.getMonth());
//        budget.setYear(budgetRequest.getYear());
//
//        // Set the user to the budget
//        budget.setUser(user);
//
//        // Save the budget and return it
//        return budgetRepository.save(budget);
//    }
    
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
    
    public Budget getBudget(Long id) {
        return budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));
    }

    
    
    
//    public List<Budget> getAllBudgetsForUser(String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        
//        return budgetRepository.findByUser(user); // Query by user object
//    }

    public List<Budget> getAllBudgets(Long userId) {
        return budgetRepository.findByUserId(userId);
    }
    
//    public Budget getBudgetByID(Long id) {
//        return budgetRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Budget not found"));
//    }

//    public Budget updateBudget(Long id, Budget budgetDetails) {
//        Budget budget = budgetRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Budget not found"));
//
//        budget.setAmount(budgetDetails.getAmount());
//        budget.setCategory(budgetDetails.getCategory());
//        budget.setMonth(budgetDetails.getMonth());
//        budget.setYear(budgetDetails.getYear());
//        
//        return budgetRepository.save(budget);
//    }

    public Budget updateBudget(Long id, Budget budgetDetails) {
        Budget budget = getBudget(id);
        budget.setAmount(budgetDetails.getAmount());
        budget.setCategory(budgetDetails.getCategory());
        return budgetRepository.save(budget);
    }    
    
    
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
    
    public boolean isOverBudget(Expense expense) {
        Budget budget = budgetRepository.findByCategoryAndUserId(expense.getCategory(), expense.getUser().getId());
        if (budget != null) {
            double totalExpenses = expenseRepository.findByCategoryAndUserId(expense.getCategory(), expense.getUser().getId())
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
            return totalExpenses + expense.getAmount() > budget.getAmount();
        }
        return false;
    }    
    
}