package com.Practice.Practice.repositories;

import com.Practice.Practice.entities.MainReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainReservationRepository extends JpaRepository<MainReservation, Long> {
    List<MainReservation> findByUserEmail(String email);
}
