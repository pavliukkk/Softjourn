package com.Practice.Practice.service;

import com.Practice.Practice.entities.User;

public interface UserService {

    User saveUser(User user);

    public void removeSessionMessage();

    public boolean checkEmail(String email);

    boolean existsByPhone(String phone);

}