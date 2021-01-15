package com.tennismatch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TennisCourtListReservations {

    private int courtNumber;
    private List<TennisCourtReservation> reservations;
}
