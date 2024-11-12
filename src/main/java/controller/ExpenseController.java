package controller;

import model.Expense;
import service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import model.User;

@RestController  // Marks the class as a REST controller to handle HTTP requests
@RequestMapping("/{userId}/expenses")  // Base URL pattern for all the routes in this controller, with a dynamic userId
public class ExpenseController {
    
    @Autowired  // Automatically injects the ExpenseService bean into this controller
    private ExpenseService expenseService;

    // POST method to create a new expense for a user
    @PostMapping
    public Expense createExpense(@PathVariable Long userId, @RequestBody Expense expense) {
        // Create a new User object and set its id to the userId from the URL path
        User user = new User();
        user.setId(userId);
        // Associate the user with the new expense
        expense.setUser(user);
        // Call the service to create the expense and return the created expense
        return expenseService.createExpense(expense);
    }

    // GET method to retrieve all expenses associated with a specific user
    @GetMapping
    public List<Expense> getAllExpenses(@PathVariable Long userId) {
        // Call the service to fetch all expenses for the given userId
        return expenseService.getAllExpenses(userId);
    }
    
    // GET method to retrieve a single expense by its id
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        // Call the service to fetch the expense with the given id
        return expenseService.getExpense(id);
    }

    // PUT method to update an expense by its id
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        // Call the service to update the expense with the given id and return the updated expense
        return expenseService.updateExpense(id, expenseDetails);
    }

    // DELETE method to remove an expense by its id
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        // Call the service to delete the expense with the given id
        expenseService.deleteExpense(id);
    }
}
