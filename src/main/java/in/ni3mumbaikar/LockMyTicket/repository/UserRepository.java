package in.ni3mumbaikar.LockMyTicket.repository;

import in.ni3mumbaikar.LockMyTicket.constant.QueryConstant;
import in.ni3mumbaikar.LockMyTicket.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserRepository(@Autowired JdbcTemplate jdbcTemplate, @Autowired PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CustomUserDetails user) {
        jdbcTemplate.update(QueryConstant.UserQueries.CREATE_NEW_USER,
                user.getUsername(), user.getFirstName(), user.getLastName(), passwordEncoder.encode(user.getPassword()), user.isEnabled());
    }

    public Optional<CustomUserDetails> getUserByUserName(String username) {
        try {
            CustomUserDetails userRecord = jdbcTemplate.queryForObject(QueryConstant.UserQueries.SELECT_USER_BY_USERNAME,
                    ((rs, rowNum) -> {
                        CustomUserDetails user = new CustomUserDetails();
                        String userName = rs.getString("user_name");
                        String password = rs.getString("password");
                        boolean isActive = rs.getBoolean("is_active");
                        user.setEnabled(isActive);
                        user.setUsername(userName);
                        user.setPassword(password);
                        return user;
                    }), username);
            return Optional.of(userRecord);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<CustomUserDetails> getAllUsers() {
        return jdbcTemplate.query(QueryConstant.UserQueries.GET_ALL_USERS,
                (rs, rowNum) -> {
                    CustomUserDetails user = new CustomUserDetails();
                    user.setUsername(rs.getString("user_name"));
                    user.setPassword(rs.getString("password"));
                    user.setEnabled(rs.getBoolean("active"));
                    return user;
                });
    }

    public boolean existsByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        return Boolean.TRUE.equals(jdbcTemplate.query(
                QueryConstant.UserQueries.CHECK_IF_USER_EXISTS,
                (rs, rowNum) -> rs.getBoolean(1),
                username
        ).stream().findFirst().orElse(false));
    }
}
