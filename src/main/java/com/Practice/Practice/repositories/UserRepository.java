package com.Practice.Practice.repositories;

import com.Practice.Practice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    boolean existsByPhone(String phone);
    User findByEmailOrPhone(String email, String phone);
    User findByResetToken(String token);
}
