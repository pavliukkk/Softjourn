package com.Practice.Practice.controllers;

import com.Practice.Practice.entities.MainReservation;
import com.Practice.Practice.entities.ReservationFromContact;
import com.Practice.Practice.entities.User;
import com.Practice.Practice.repositories.MainReservationRepository;
import com.Practice.Practice.repositories.ReservationRepository;
import com.Practice.Practice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainReservationRepository mainReservationRepository;

    @PostMapping("/contact")
    public String contactReservation(@RequestParam String name, @RequestParam String surname, @RequestParam String email,
                                     @RequestParam String phone, @RequestParam Date date, @RequestParam String time, @RequestParam String people) {

        ReservationFromContact reservationContact = new ReservationFromContact(name, surname, email, phone, date, time, people);
        reservationService.save(reservationContact);
        return "redirect:/menu";
    }
    @PostMapping("/mainReservation")
    public String makeReservation(@RequestParam Date date, @RequestParam String time,
                                  @RequestParam String people, Principal principal) {
        if (principal != null) {
            String userEmail = principal.getName();
            User user = userRepository.findByEmail(userEmail);

            MainReservation reservation = new MainReservation();
            reservation.setDate(date);
            reservation.setTime(time);
            reservation.setPeople(people);
            reservation.setUserName(user.getName());
            reservation.setUserSurname(user.getSurname());
            reservation.setUserPhone(user.getPhone());
            reservation.setUserEmail(user.getEmail());

            mainReservationRepository.save(reservation);
            return "redirect:/portfolio";
        } else {
            return "redirect:/registration";
        }
    }



}
