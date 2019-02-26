package restaurant.controller;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class Order {
    @NotEmpty (message="Name is required")
    private String name;
    @NotEmpty (message="Email is required")
    @Size(min = 1, max = 100)
    @Pattern(regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$")
    private String email;
    @Digits(integer=11, fraction=0, message="Phone number should contain 11 numbers")
    private String phone;
    @NotBlank(message="Date is required")
    private String date;

}