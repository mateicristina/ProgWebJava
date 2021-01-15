package com.tennismatch.demo.service;

import com.tennismatch.demo.domain.TennisCourtReservation;
import com.tennismatch.demo.exception.TennisCourtAlreadyReservedException;
import com.tennismatch.demo.repository.TennisCourtReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class ReservationService {

    private final TennisCourtReservationRepository tennisCourtReservationRepository;

    @Autowired
    public ReservationService(TennisCourtReservationRepository tennisCourtReservationRepository) {
        this.tennisCourtReservationRepository = tennisCourtReservationRepository;
    }

    public void validateReservationTimeIsFreeForCourt(TennisCourtReservation reservation) {
        Optional<TennisCourtReservation> alreadyExistingReservationForCourtAtTime = tennisCourtReservationRepository
                .findByCourtIdAndDateAndTimeInterval(
                        reservation.getCourtId(),
                        new Date(reservation.getDate().getTime()),
                        reservation.getStartHour(),
                        reservation.getEndHour()
                );
        if (alreadyExistingReservationForCourtAtTime.isPresent()) {
            throw new TennisCourtAlreadyReservedException();
        }
    }
}
