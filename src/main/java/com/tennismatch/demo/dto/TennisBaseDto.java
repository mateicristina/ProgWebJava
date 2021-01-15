package com.tennismatch.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TennisBaseDto {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String address;
}
