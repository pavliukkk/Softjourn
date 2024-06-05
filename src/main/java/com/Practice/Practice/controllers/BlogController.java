package com.Practice.Practice.controllers;

import com.Practice.Practice.entities.User;
import com.Practice.Practice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class BlogController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/blogs")
    public String defaultHomepage(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }
        return "blog.html";
    }

    @GetMapping("/blog-post")
    public String customHomepage(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
        }
        return "blog-post.html";
    }
}
