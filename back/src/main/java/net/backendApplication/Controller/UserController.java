package net.backendApplication.Controller;

import net.backendApplication.AuthResponse;
import net.backendApplication.Entities.User;
import net.backendApplication.JwtUtil;
import net.backendApplication.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping()
    public List<User> getUsers() {
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userServices.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userServices.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
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
    public void deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userServices.findByLastName(user.getLastName());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(existingUser.getLastName());
            AuthResponse response = new AuthResponse();
            response.setToken(token);
            response.setId(existingUser.getId()); // Set the ID of the user in the response
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
