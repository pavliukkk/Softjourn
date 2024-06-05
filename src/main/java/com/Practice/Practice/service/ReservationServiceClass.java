package com.Practice.Practice.service;

import com.Practice.Practice.entities.MainReservation;
import com.Practice.Practice.repositories.MainReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceClass {

    @Autowired
    private MainReservationRepository reservationRepository;

    public List<MainReservation> findReservationsByEmail(String email) {
        return reservationRepository.findByUserEmail(email);
    }
}
