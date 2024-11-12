package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// The 'User' class represents a user in the application and is mapped to the 'app_user' table in the database.
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "expenses", "budgets"})  // Ignore uninitialized lazy-loaded properties during serialization
@Table(name = "app_user")  // Maps this entity to the 'app_user' table in the database
public class User {

    // 'id' is the primary key for the User entity, generated automatically by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 'username' is a required field, unique for each user.
    @Column(nullable = false, unique = true)
    private String username;

    // 'password' is a required field for user authentication.
    @Column(nullable = false)
    private String password;

    // 'email' is a required field and must be unique for each user.
    @Column(nullable = false, unique = true)
    private String email;

    // A one-to-many relationship with the 'Expense' entity, indicating that a user can have multiple expenses.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)  // Cascade all operations to related expenses, remove orphaned expenses when the user is deleted
    private List<Expense> expenses;

    // A one-to-many relationship with the 'Budget' entity, indicating that a user can have multiple budgets.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)  // Cascade all operations to related budgets, remove orphaned budgets when the user is deleted
    private List<Budget> budgets;

    // Constructor with fields for convenience (optional), initializing 'username', 'password', and 'email'.
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Default no-argument constructor (required for JPA)
    public User() {}

    // Getters and Setters

    // Getter for 'id' field
    public Long getId() {
        return id;
    }

    // Setter for 'id' field
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for 'username' field
    public String getUsername() {
        return username;
    }

    // Setter for 'username' field
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for 'password' field
    public String getPassword() {
        return password;
    }

    // Setter for 'password' field
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for 'email' field
    public String getEmail() {
        return email;
    }

    // Setter for 'email' field
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for the list of 'expenses' associated with this user
    public List<Expense> getExpenses() {
        return expenses;
    }

    // Setter for the list of 'expenses'
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    // Getter for the list of 'budgets' associated with this user
    public List<Budget> getBudgets() {
        return budgets;
    }

    // Setter for the list of 'budgets'
    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }
}
