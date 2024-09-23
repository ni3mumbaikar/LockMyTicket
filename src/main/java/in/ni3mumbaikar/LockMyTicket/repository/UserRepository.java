package in.ni3mumbaikar.LockMyTicket.repository;

import in.ni3mumbaikar.LockMyTicket.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(User user) {
        jdbcTemplate.update("INSERT INTO lmt.users (id, username, password, active) VALUES (?, ?, ?, ?)",
                user.getId(), user.getUsername(), user.getPassword(), user.getActive());
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM lmt.users",
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("user_name"));
                    user.setPassword(rs.getString("password"));
                    user.setActive(rs.getBoolean("active"));
                    return user;
                });
    }

    // Other CRUD methods (update, delete) can be added here
}
