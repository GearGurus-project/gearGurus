package net.backendApplication.Services;

import net.backendApplication.Entities.User;
import net.backendApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public long getUsersCount() {
        return userRepository.count();
    }


}
