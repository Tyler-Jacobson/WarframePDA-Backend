package com.warframepda.www.controllers;

import com.warframepda.www.models.User;
import com.warframepda.www.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllUsers() {
        List<User> myUsers = userServices.findAllUsers();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @PostMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser) {

        newUser.setUserid(0);
        newUser = userServices.save(newUser);

        return new ResponseEntity<>(newUser, HttpStatus.OK);

    }


}
