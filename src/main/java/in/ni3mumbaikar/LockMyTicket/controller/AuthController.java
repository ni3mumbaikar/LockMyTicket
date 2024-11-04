package in.ni3mumbaikar.LockMyTicket.controller;

import in.ni3mumbaikar.LockMyTicket.model.AuthRequest;
import in.ni3mumbaikar.LockMyTicket.model.CustomUserDetails;
import in.ni3mumbaikar.LockMyTicket.service.CustomDatabaseUserDetailsManager;
import in.ni3mumbaikar.LockMyTicket.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomDatabaseUserDetailsManager customDatabaseUserDetailsManager;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(CustomDatabaseUserDetailsManager customDatabaseUserDetailsManager,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.customDatabaseUserDetailsManager = customDatabaseUserDetailsManager;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> createUser(@RequestBody CustomUserDetails user) throws Exception {
        customDatabaseUserDetailsManager.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate the user using the AuthenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // If successful, generate a token
            String token = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
