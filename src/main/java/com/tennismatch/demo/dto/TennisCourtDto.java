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
public class TennisCourtDto {

    private int id;
    @NotNull
    private int number;
    @NotNull
    private int baseId;
}
