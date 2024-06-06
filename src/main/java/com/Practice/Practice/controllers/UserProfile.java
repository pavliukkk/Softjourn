package com.Practice.Practice.controllers;

import com.Practice.Practice.entities.MainReservation;
import com.Practice.Practice.entities.User;
import com.Practice.Practice.service.ReservationServiceImpl;
import com.Practice.Practice.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class UserProfile {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
    public String saveProfileChanges(@ModelAttribute("user") User user, BindingResult result,  HttpServletRequest request, HttpServletResponse response) {
        userService.updateUser(user);
        if(user == null) {
            return "redirect:/login";
        }else {
            return "redirect:/profile";
        }
    }

    @PostMapping("/delete_account")
    public String deleteAccount(HttpServletRequest request) {
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            // Паролі не співпадають або не заповнені
            return "redirect:/profile?error=passwords_not_matching";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();
        User currentUser = userService.findByEmail(currentUsername);

        if (currentUser == null || !passwordEncoder.matches(password, currentUser.getPassword())) {
            // Невірний пароль
            return "redirect:/profile?error=wrong_password";
        }

        // Видаляємо користувача
        userService.deleteUser(currentUser.getId());

        // Виходимо з сесії
        SecurityContextHolder.clearContext();

        return "redirect:/";
    }

    @PostMapping("/deleteReservation")
    public String deleteReservation(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        boolean isDeleted = reservationService.deleteReservationById(id);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Reservation deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete reservation.");
        }
        return "redirect:/reservations";
    }

    @GetMapping("/change_password")
    public String changePasswordPage(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByEmail(username);
        model.addAttribute("user", user);
        return "change_password";
    }

    @GetMapping("/reset_password")
    public String resetPasswordPage(){
        return "password-recover";
    }

    @PostMapping("/reset_password")
    public String sendResetEmail(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        try {
            userService.sendResetEmail(email);
            redirectAttributes.addFlashAttribute("email_sent", true);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/reset_password";
    }

    @GetMapping("/reset_password_confirm")
    public String resetPasswordPageConfirm(){
        return "reset_password_confirm";
    }

    @PostMapping("/reset_password_confirm")
    public String changePassword(@RequestParam("password") String password,
                                 @RequestParam("confirm_password") String confirmPassword,
                                 @RequestParam("token") String token,
                                 RedirectAttributes redirectAttributes) {
        // Перевірити чи паролі співпадають
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("different_passwords", true);
            return "redirect:/reset_password_confirm?token=" + token;
        }
        System.out.println(token);
        // Отримати користувача за допомогою токену
        User user = userService.getUserByPasswordResetToken(token);

        if (user == null) {
            // Якщо користувача не знайдено за токеном, можливо, токен недійсний або минув строк дії
            redirectAttributes.addFlashAttribute("link_invalid", true);
            return "redirect:/reset_password_confirm?token=" + token;
        }

        // Оновити пароль користувача
        userService.updatePassword(user, password);

        // Перенаправити на сторінку профілю або іншу потрібну
        return "redirect:/profile";
    }
}
