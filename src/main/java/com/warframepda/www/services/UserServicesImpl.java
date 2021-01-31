package com.warframepda.www.services;

import com.warframepda.www.models.User;
import com.warframepda.www.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userrepos;

    @Override
    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();

        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User save(User user) {

        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setUserrole(user.getUserrole());


        return userrepos.save(newUser);
    }
}
