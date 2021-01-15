package com.tennismatch.demo.dto;

import com.tennismatch.demo.domain.TennisCourtReservation;
import lombok.*;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PracticeDto {

    private int id;
    @NotNull
    private int userId;
    private TennisCourtReservation reservation;
}
