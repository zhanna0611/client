package restaurant.security;

import lombok.Data;
//import org.springframework.security.crypto.password.PasswordEncoder;
import restaurant.model.User;

@Data
public class RegistrationForm {
    private String username;
    private String name;
    private String password;
    private String phoneNumber;
    private String role;

//    public User toUser(PasswordEncoder passwordEncoder) {
//        return new User(
//                username, name, passwordEncoder.encode(password),phoneNumber,role);
//    }


}
