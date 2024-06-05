package com.Practice.Practice.controllers;

import com.Practice.Practice.entities.User;
import com.Practice.Practice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String defaultHomepage(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }

        return "index";
    }

    @GetMapping("/FoodZero")
    public String customHomepage(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }
        return "about";
    }

    @GetMapping("/portfolio")
    public String portfolio(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }
        return "portfolio";
    }

    @GetMapping("/soon")
    public String soon(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }
        return "soon";
    }

    @GetMapping("/menu")
    public String menu(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }
        return "menu";
    }

    @GetMapping("/contact")
    public String contactPage(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }
        return "contact";
    }

}