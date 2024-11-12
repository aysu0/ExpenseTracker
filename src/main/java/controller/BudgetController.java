package controller;

import model.Budget;
import service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import model.User;

@RestController  // Marks the class as a REST controller to handle HTTP requests
@RequestMapping("{userId}/budgets")  // Base URL pattern for all the routes in this controller, with a dynamic userId
public class BudgetController {
    
    @Autowired  // Automatically injects the BudgetService bean into this controller
    private BudgetService budgetService;

    // POST method to create a new budget for a user
    @PostMapping
    public Budget createBudget(@PathVariable Long userId, @RequestBody Budget budget) {
        // Create a new User object and set its id to the userId from the URL path
        User user = new User();
        user.setId(userId);
        // Associate the user with the new budget
        budget.setUser(user);
        // Call the service to create the budget and return the created budget
        return budgetService.createBudget(budget);
    }    

    // GET method to retrieve all budgets associated with a specific user
    @GetMapping()
    public List<Budget> getAllBudgets(@PathVariable Long userId) {
        // Call the service to fetch all budgets for the given userId
        return budgetService.getAllBudgets(userId);
    }
    
    // GET method to retrieve a single budget by its id
    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable("id") Long id) {
        // Call the service to fetch the budget with the given id
        return budgetService.getBudget(id); 
    }

    // PUT method to update a budget by its id
    @PutMapping("/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget budgetDetails) {
        // Call the service to update the budget with the given id and return the updated budget
        return budgetService.updateBudget(id, budgetDetails);
    }

    // DELETE method to remove a budget by its id
    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable Long id) {
        // Call the service to delete the budget with the given id
        budgetService.deleteBudget(id);
    }
}
