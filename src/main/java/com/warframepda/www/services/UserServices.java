package com.warframepda.www.services;

import com.warframepda.www.models.User;

import java.util.List;

public interface UserServices {

    List<User> findAllUsers();

    User save(User user);
}
