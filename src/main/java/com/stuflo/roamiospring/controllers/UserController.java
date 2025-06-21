package com.stuflo.roamiospring.controllers;

import com.stuflo.roamiospring.models.User;
import com.stuflo.roamiospring.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public User getUser() {
        return userRepository.findById(1L).orElse(null);
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
