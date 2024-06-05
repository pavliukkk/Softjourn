package com.Practice.Practice.repositories;

import com.Practice.Practice.entities.ReservationFromContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationFromContact, Long> {



}
