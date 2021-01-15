package com.tennismatch.demo.service;

import com.tennismatch.demo.domain.NewUser;
import com.tennismatch.demo.domain.User;
import com.tennismatch.demo.exception.EntityNotFoundException;
import com.tennismatch.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(NewUser request) {
        return repository.save(request);
    }

    public User getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User"));
    }

    public User getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("User"));
    }

    public User editUser(User user) {
        return repository.updateUser(user);
    }
}