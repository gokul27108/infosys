package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateEmailException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(User user) {

        // ✅ DUPLICATE EMAIL CHECK
        repository.findByEmail(user.getEmail())
                .ifPresent(existing -> {
                    throw new DuplicateEmailException("Email already exists");
                });

        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, User user) {
        User existing = getUser(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return repository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
