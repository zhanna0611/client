package restaurant.model;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String name;
    private String password;
    private String phoneNumber;
    private String role;
}
