package restaurant.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import restaurant.model.User;

@Data
public class RegistrationForm {
    private String username;
    private String name;
    private String password;
    private String phone;
    private String role;
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, name, passwordEncoder.encode(password),phone,role);
        }
        }
