package restaurant.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import restaurant.model.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty(message="Username is required")
    private String username;
//    @NotEmpty(message="Name is required")
//    private String name;
    @NotEmpty(message="Password is required")
    private String password;
//    @NotEmpty(message="PhoneNumber is required")
//    private String phoneNumber;
    @NotEmpty(message="Role is required")
    private String role;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),role);
    }


}
