package com.Practice.Practice.controllers;

import com.Practice.Practice.entities.MainReservation;
import com.Practice.Practice.entities.User;
import com.Practice.Practice.service.ReservationServiceClass;
import com.Practice.Practice.service.UserService;
import com.Practice.Practice.service.UserServiceClass;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserProfile {

    @Autowired
    private UserServiceClass userService;

    @Autowired
    private ReservationServiceClass reservationService;

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication, Model model) {
        // Отримання користувача з поточної сесії
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        // Отримання бронювань користувача
        List<MainReservation> reservations = reservationService.findReservationsByEmail(email);

        model.addAttribute("user", user);
        model.addAttribute("reservations", reservations);

        return "profile";
    }

    @GetMapping("/profile-edit")
    public String getProfileEditPage(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByEmail(username);
        model.addAttribute("user", user);
        return "edit_profile";
    }

    @PostMapping("/profile-edit")
    public String saveProfileChanges(User updatedUser) {
        userService.updateUser(updatedUser);
        return "redirect:/profile";
    }

}
