package restaurant.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import restaurant.model.Order;

import restaurant.model.User;
import restaurant.model.UserDto;
import restaurant.repository.TacoCloudClient;
import restaurant.repository.UserRepository;
import restaurant.security.RegistrationForm;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    private TacoCloudClient tacoCloudClient;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(
            TacoCloudClient tacoCloudClient,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.tacoCloudClient = tacoCloudClient;
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }



    @PostMapping
    public String processRegistration(RegistrationForm form) {
        System.out.println("form = " + form);

        //System.out.println("form.toUser(passwordEncoder) = " + form.toUser());
        User user = form.toUser(passwordEncoder);
        System.out.println("form = " + form);

        tacoCloudClient.saveUser(user);
        //tacoCloudClient.saveUser(form);

        return "redirect:/login";
    }

}

