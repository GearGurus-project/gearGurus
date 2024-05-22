package net.backendApplication.Controller;

import net.backendApplication.Entities.User;
import net.backendApplication.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServices userServices;
    @GetMapping()
    public List<User> getUsers() {
        return userServices.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userServices.getUserById(id);
    }
    @PostMapping
    public User createstudent(@RequestBody User user) {
        return userServices.saveUser(user);
    }
    @PutMapping("/{id}")
    public User updatestudent(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userServices.getUserById(id);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            return userServices.saveUser(existingUser);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deletestudent(@PathVariable Long id) {
        userServices.deleteUser(id);
    }
}
