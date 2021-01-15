package com.tennismatch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TennisCourtReservation {

    private int id;
    @NotNull
    private int userId1;
    private Integer userId2;
    @NotNull
    private int courtId;
    private Date date;
    private Time startHour;
    private Time endHour;
}
