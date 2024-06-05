package com.Practice.Practice.service;

import com.Practice.Practice.entities.ReservationFromContact;
import com.Practice.Practice.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public ReservationFromContact saveReservationContact(ReservationFromContact reservationFromContact) {
        ReservationFromContact newReservation = reservationRepository.save(reservationFromContact);

        return newReservation;
    }
}
