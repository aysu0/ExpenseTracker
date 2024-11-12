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

    // Injecting the ExpenseRepository to interact with the database for expense-related operations
    @Autowired
    private ExpenseRepository expenseRepository;
    
    // Injecting the UserRepository to interact with the database for user-related operations
    @Autowired
    private UserRepository userRepository;

    // Injecting the BudgetService to manage budget-related logic
    @Autowired
    private BudgetService budgetService;
    
    // Injecting the BudgetRepository to interact with the database for budget-related operations
    @Autowired
    private BudgetRepository budgetRepository;

    // Creates a new expense and checks if it exceeds the user's budget for that category, month, and year
    public Expense createExpense(Expense expense) {
        // Retrieve the budget for the user, category, month, and year
        Budget budget = budgetRepository.findByUserAndCategoryAndMonthAndYear(
                expense.getUser(), expense.getCategory(), expense.getMonth(), expense.getYear());

        // If a budget exists, check if the expense exceeds the budget
        if (budget != null) {
            // Retrieve the total amount of expenses for the given category, month, and year
            Double totalExpenseAmount = expenseRepository.findTotalAmountByUserAndCategoryAndMonthAndYear(
                    expense.getUser(), expense.getCategory(), expense.getMonth(), expense.getYear());

            // If the total expenses (current expenses + new expense) exceed the budget, throw an exception
            if (totalExpenseAmount + expense.getAmount() > budget.getAmount()) {
                throw new IllegalArgumentException("Expense exceeds budget for this category, month, and year.");
            }
        }

        // Save the expense if it is within the budget (or if there is no budget set for the category)
        return expenseRepository.save(expense);
    }
    
    // Retrieves an expense by its ID and throws an exception if not found
    public Expense getExpense(Long id) {
        // Uses the repository to find the expense by ID and throws an exception if not found
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    // Retrieves all expenses for a user by their userId
    public List<Expense> getAllExpenses(Long userId) {
        // Retrieves the list of expenses associated with a specific user from the repository
        return expenseRepository.findByUserId(userId);
    }

    // Updates the details of an existing expense
    public Expense updateExpense(Long id, Expense expenseDetails) {
        // Retrieves the expense to be updated by its ID
        Expense expense = getExpense(id);

        // Updates the expense details with the new values provided
        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDescription(expenseDetails.getDescription());
        expense.setMonth(expenseDetails.getMonth());
        expense.setYear(expenseDetails.getYear());

        // Saves and returns the updated expense
        return expenseRepository.save(expense);
    }

    // Deletes an expense by its ID
    public void deleteExpense(Long id) {
        // Deletes the expense with the given ID from the repository
        expenseRepository.deleteById(id);
    }
}
