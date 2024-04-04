package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.entity.UserStory;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserStoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
     User newUser(@RequestBody User user) {
        return repository.save(user);
    }


    @GetMapping
    public List<User> getAllUser() {
        return repository.findAll();
    }
}
