package in.ni3mumbaikar.LockMyTicket.controller;

import in.ni3mumbaikar.LockMyTicket.model.CustomUserDetails;
import in.ni3mumbaikar.LockMyTicket.service.CustomDatabaseUserDetailsManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CustomDatabaseUserDetailsManager customDatabaseUserDetailsManager;

    public UserController(CustomDatabaseUserDetailsManager customDatabaseUserDetailsManager) {
        this.customDatabaseUserDetailsManager = customDatabaseUserDetailsManager;
    }

    @GetMapping
    public ResponseEntity<List<CustomUserDetails>> getAllUsers(){
        ArrayList<CustomUserDetails> customUserDetails = customDatabaseUserDetailsManager.getAllUsers();
        return ResponseEntity.ok(customUserDetails);
    }

}
