package restaurant.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import restaurant.model.User;
import restaurant.model.UserDto;
import restaurant.repository.UserRepository;
import restaurant.security.RegistrationForm;


@Controller
@RequestMapping("/register")
public class RegistrationController {
   private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        System.out.println("RegistrationController.processRegistration");
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/";
    }
}