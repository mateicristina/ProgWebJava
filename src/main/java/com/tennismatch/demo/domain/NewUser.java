package com.tennismatch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUser {

    private int id;
    private String name;
    private Integer age;
    private String city;
    private String phoneNumber;
    private String email;
    private String password;
}