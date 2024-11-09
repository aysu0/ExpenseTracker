
package controller;

import model.Budget;
import service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import model.User;

@RestController
@RequestMapping("{userId}/budgets")
public class BudgetController {
    
    @Autowired
    private BudgetService budgetService;

//    @PostMapping("/create")
//    public Budget createBudget(@RequestBody Budget budget, @RequestParam String username) {
//        return budgetService.createBudget(budget, username);
//    }
    
//    @PostMapping("/create")
//    public Budget createBudget(@RequestBody BudgetRequest budgetRequest) {
//        return budgetService.createBudget(budgetRequest); 
//    }
//    
    @PostMapping
    public Budget createBudget(@PathVariable Long userId, @RequestBody Budget budget) {
        User user = new User();
        user.setId(userId);
        budget.setUser(user);
        return budgetService.createBudget(budget);
    }    

    
    
//    @GetMapping("/user")
//    public List<Budget> getBudgetsForUser(@RequestParam String username) {
//        return budgetService.getAllBudgetsForUser(username);
//    }    
//    
    
    @GetMapping()
    public List<Budget> getAllBudgets(@PathVariable Long userId) {
        return budgetService.getAllBudgets(userId);
    }
    
    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable("id") Long id)
    {
        return budgetService.getBudget(id); 
    }

    @PutMapping("/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget budgetDetails) {
        return budgetService.updateBudget(id, budgetDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
    }
    
    @GetMapping("/test")
    public String testEndpoint() {
        return "Budget controller is working!";
    }

} 