package com.icici.demo.controllers;


import org.springframework.web.bind.annotation.RestController;

import com.icici.demo.entities.Users;
import com.icici.demo.repos.UserRepository;

import lombok.extern.slf4j.Slf4j;

//import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<Users> fetchAllTrips() {
        // logic to fetch from DB
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Users fetchATrip(@PathVariable("id") int id) {
        Optional<Users> userFound = userRepository.findById(id);
        if (userFound.isPresent()) {
            log.debug("User Found " + userFound.get());
            return userFound.get();
        } else {
            log.warn("User Not Found with id " + id);
            throw new UserNotFoundException("Trip not found with id " + id);
        }
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrips(@RequestBody Users users) {
        userRepository.save(users);
    }

    @DeleteMapping("/users/{id}")
    public void deleteTrip(@PathVariable("id") int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

