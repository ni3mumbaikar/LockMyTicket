package in.ni3mumbaikar.LockMyTicket.service;

import in.ni3mumbaikar.LockMyTicket.exception.UserAlreadyExistException;
import in.ni3mumbaikar.LockMyTicket.model.CustomUserDetails;
import in.ni3mumbaikar.LockMyTicket.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomDatabaseUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;

    public CustomDatabaseUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new user with the supplied details.
     *
     * @param user
     */
    @Override
    public void createUser(UserDetails user) {
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new UserAlreadyExistException("Username already exists");
            }
            userRepository.createUser((CustomUserDetails) user);
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException(e);
        } catch (Exception exception) {
            // TODO: log different error
            throw new RuntimeException(exception);
        }
    }

    /**
     * Update the specified user.
     *
     * @param user
     */
    @Override
    public void updateUser(UserDetails user) {

    }

    /**
     * Remove the user with the given login name from the system.
     *
     * @param username
     */
    @Override
    public void deleteUser(String username) {

    }

    /**
     * Modify the current user's password. This should change the user's password in the
     * persistent user repository (database, LDAP etc).
     *
     * @param oldPassword current password (for re-authentication if required)
     * @param newPassword the password to change to
     */
    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    /**
     * Check if a user with the supplied login name exists in the system.
     *
     * @param username
     */
    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUserDetails> user = userRepository.getUserByUserName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " is not available");
        }
        return user.get();
    }

    public ArrayList<CustomUserDetails> getAllUsers() {
        return (ArrayList<CustomUserDetails>) userRepository.getAllUsers();
    }
}
