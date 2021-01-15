package com.tennismatch.demo.service;

import com.tennismatch.demo.domain.User;
import com.tennismatch.demo.exception.EntityNotFoundException;
import com.tennismatch.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final UserRepository repository;

    @Autowired
    public SessionService(UserRepository repository) {
        this.repository = repository;
    }

    public User login(String email, String password) {
        return repository.findUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new EntityNotFoundException("User"));
    }
}
