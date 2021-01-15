package com.tennismatch.demo.controller;

import com.tennismatch.demo.domain.User;
import com.tennismatch.demo.dto.UserDto;
import com.tennismatch.demo.mapper.UserMapper;
import com.tennismatch.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private UserMapper mapper;
    private UserService service;

    @Autowired
    public UserController(UserMapper mapper, UserService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PutMapping(path = "/edit-profile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> editUser(@Valid @RequestBody UserDto request) {
        User saved = service.editUser(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.OK);
    }

}
