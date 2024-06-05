package com.Practice.Practice.repositories;

import com.Practice.Practice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByEmail(String email);

    public User findByEmail(String email);

    boolean existsByPhone(String phone);

    User findByEmailOrPhone(String username, String username1);
}
