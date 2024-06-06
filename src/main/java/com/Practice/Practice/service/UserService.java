package com.Practice.Practice.service;

import com.Practice.Practice.entities.User;

public interface UserService {

    User saveUser(User user);

    void removeSessionMessage();

    boolean checkEmail(String email);

    boolean existsByPhone(String phone);

    User findByEmail(String email);

    User findById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    void changePassword(String username, String oldPassword, String newPassword);

    void sendResetEmail(String email);

    void updatePassword(User user, String newPassword);

    User getUserByPasswordResetToken(String token);
}
