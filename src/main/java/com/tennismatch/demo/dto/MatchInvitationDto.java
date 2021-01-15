package com.tennismatch.demo.dto;

import com.tennismatch.demo.domain.TennisCourtReservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchInvitationDto {

    private int id;
    @NotNull
    private int senderUserId;
    @NotNull
    private int receiverUserId;
    private TennisCourtReservation reservation;
}
