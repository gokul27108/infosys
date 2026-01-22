package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    // Constructor injection
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return service.createUser(user);
    }


    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id,
                       @Valid @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteUser(id);
        return "User deleted successfully";
    }
}
