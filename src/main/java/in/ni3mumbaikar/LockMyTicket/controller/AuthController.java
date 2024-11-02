package in.ni3mumbaikar.LockMyTicket.controller;

import in.ni3mumbaikar.LockMyTicket.model.CustomUserDetails;
import in.ni3mumbaikar.LockMyTicket.service.CustomDatabaseUserDetailsManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomDatabaseUserDetailsManager customDatabaseUserDetailsManager;

    public AuthController(CustomDatabaseUserDetailsManager customDatabaseUserDetailsManager) {
        this.customDatabaseUserDetailsManager = customDatabaseUserDetailsManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> createUser(@RequestBody CustomUserDetails user) throws Exception {
        customDatabaseUserDetailsManager.createUser(user);
        return ResponseEntity.ok().build();
    }

}
