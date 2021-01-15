package com.tennismatch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Practice {
    private int id;
    @NotNull
    private int userId;
    private TennisCourtReservation reservation;
}
