
package controller;


import model.Expense;
import service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import model.User;

@RestController
@RequestMapping("/{userId}/expenses")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense createExpense(@PathVariable Long userId, @RequestBody Expense expense) {
        User user = new User();
        user.setId(userId);
        expense.setUser(user);
        return expenseService.createExpense(expense);
    }
    
    @GetMapping
    public List<Expense> getAllExpenses(@PathVariable Long userId) {
        return expenseService.getAllExpenses(userId);
    }
    
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpense(id);
    }
    

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        return expenseService.updateExpense(id, expenseDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}

