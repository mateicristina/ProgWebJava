package com.tennismatch.demo.service;

import com.tennismatch.demo.domain.MatchInvitation;
import com.tennismatch.demo.domain.TennisCourtReservation;
import com.tennismatch.demo.exception.TennisCourtAlreadyReservedException;
import com.tennismatch.demo.repository.TennisCourtReservationRepository;
import com.tennismatch.demo.repository.TennisMatchInvitationRepository;
import com.tennismatch.demo.repository.TennisMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TennisMatchService {

    private final TennisMatchRepository tennisMatchRepository;
    private final TennisMatchInvitationRepository tennisMatchInvitationRepository;
    private final TennisCourtReservationRepository tennisCourtReservationRepository;
    private final ReservationService reservationService;

    @Autowired
    public TennisMatchService(
            TennisMatchRepository tennisMatchRepository,
            TennisMatchInvitationRepository tennisMatchInvitationRepository,
            TennisCourtReservationRepository tennisCourtReservationRepository,
            ReservationService reservationService
            ) {
        this.tennisMatchRepository = tennisMatchRepository;
        this.tennisMatchInvitationRepository = tennisMatchInvitationRepository;
        this.tennisCourtReservationRepository = tennisCourtReservationRepository;
        this.reservationService = reservationService;
    }

    public MatchInvitation createInvitation(MatchInvitation invitation) {
        reservationService.validateReservationTimeIsFreeForCourt(invitation.getReservation());
        TennisCourtReservation reservation = tennisCourtReservationRepository.create(invitation.getReservation());
        return tennisMatchInvitationRepository.save(invitation, reservation.getId());
    }

    public List<MatchInvitation> getInvitations(int userId) {
        return tennisMatchInvitationRepository.finaAllByUserId(userId);
    }

    public void acceptInvitation(int id) {
        tennisMatchInvitationRepository.deleteById(id);
    }

    public void refuseInvitation(int id) {
        Optional<MatchInvitation> matchInvitation = tennisMatchInvitationRepository.getById(id);
        tennisMatchInvitationRepository.deleteById(id);
        if (matchInvitation.isPresent()) {
            tennisCourtReservationRepository.deleteById(matchInvitation.get().getReservation().getId());
        }
    }
}
