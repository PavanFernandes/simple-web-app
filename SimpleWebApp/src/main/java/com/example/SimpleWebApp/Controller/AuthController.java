package com.example.SimpleWebApp.Controller;

import com.example.SimpleWebApp.entities.User;
import com.example.SimpleWebApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private IUserService userService;
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public AuthController(IUserService userService, InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.userService = userService;
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }


    @PostMapping("/signup")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "user-registration-form";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login-page";
    }

    @PostMapping("/processRegistrationForm")
    public String saveUser(@ModelAttribute("user") User user, Model model){
        if (userService.findByUsername(user.getUsername()) != null){
            model.addAttribute("error", "User already exists, please login.");
            return register(model);
        }
        userService.save(user);
        model.addAttribute("user", user);
        return loginPage();
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
}
