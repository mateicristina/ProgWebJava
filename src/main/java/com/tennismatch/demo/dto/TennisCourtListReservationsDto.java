package com.tennismatch.demo.dto;

import com.tennismatch.demo.domain.TennisCourtReservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TennisCourtListReservationsDto {

    @NotNull
    private int courtNumber;
    private List<TennisCourtReservation> reservations;
}
