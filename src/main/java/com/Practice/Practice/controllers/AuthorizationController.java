package com.Practice.Practice.controllers;

import com.Practice.Practice.config.CustomUserDetailsService;
import com.Practice.Practice.entities.User;
import com.Practice.Practice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute User user, HttpSession session, Model model) {
        boolean emailExists = userService.checkEmail(user.getEmail());
        boolean numberExists = userService.existsByPhone(user.getPhone());;

        if (emailExists || numberExists) {
            model.addAttribute("errorUserExist", true);
            return "registration";
        }

        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            session.setAttribute("msg", "Registered Successfully");
        } else {
            session.setAttribute("msg", "Something went wrong on the server");
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
