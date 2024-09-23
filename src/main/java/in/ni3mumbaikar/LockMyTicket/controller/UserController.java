package in.ni3mumbaikar.LockMyTicket.controller;

import in.ni3mumbaikar.LockMyTicket.entity.User;
import in.ni3mumbaikar.LockMyTicket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userRepository.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
