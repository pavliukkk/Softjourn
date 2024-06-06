package com.Practice.Practice.service;

import com.Practice.Practice.entities.MainReservation;
import com.Practice.Practice.entities.ReservationFromContact;
import com.Practice.Practice.repositories.MainReservationRepository;
import com.Practice.Practice.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MainReservationRepository mainReservationRepository;

    @Override
    public ReservationFromContact saveReservationContact(ReservationFromContact reservationFromContact) {
        ReservationFromContact newReservation = reservationRepository.save(reservationFromContact);

        return newReservation;
    }

    public List<MainReservation> findReservationsByEmail(String email) {
        return mainReservationRepository.findByUserEmail(email);
    }

    public boolean deleteReservationById(Long id) {
        try {
            reservationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // Handle exception if necessary
            return false;
        }
    }


}
