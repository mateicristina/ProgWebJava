package com.tennismatch.demo.controller;

import com.tennismatch.demo.domain.User;
import com.tennismatch.demo.dto.LoginDto;
import com.tennismatch.demo.dto.NewUserDto;
import com.tennismatch.demo.dto.UserDto;
import com.tennismatch.demo.mapper.NewUserMapper;
import com.tennismatch.demo.mapper.SessionMapper;
import com.tennismatch.demo.mapper.UserMapper;
import com.tennismatch.demo.service.SessionService;
import com.tennismatch.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("")
public class SessionController {

    private UserMapper mapper;
    private NewUserMapper newUserMapper;
    private SessionService service;
    private UserService userService;

    @Autowired
    public SessionController(UserMapper mapper, NewUserMapper newUserMapper, SessionService service, UserService userService) {
        this.mapper = mapper;
        this.newUserMapper = newUserMapper;
        this.service = service;
        this.userService = userService;
    }

    @PostMapping(path = "/register-user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody NewUserDto request) {
        User saved = userService.create(newUserMapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginDto request) {
        System.out.println(request);
        User saved = service.login(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.OK);
    }
}
