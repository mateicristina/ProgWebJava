package com.tennismatch.demo.service;

import com.tennismatch.demo.domain.Practice;
import com.tennismatch.demo.domain.TennisCourtReservation;
import com.tennismatch.demo.exception.TennisCourtAlreadyReservedException;
import com.tennismatch.demo.repository.PracticeRepository;
import com.tennismatch.demo.repository.TennisCourtReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PracticeService {

    private final PracticeRepository repository;
    private final TennisCourtReservationRepository tennisCourtReservationRepository;
    private final ReservationService reservationService;

    @Autowired
    public PracticeService(
            PracticeRepository repository,
            TennisCourtReservationRepository tennisCourtReservationRepository,
            ReservationService reservationService
    ) {
        this.repository = repository;
        this.tennisCourtReservationRepository = tennisCourtReservationRepository;
        this.reservationService = reservationService;
    }

    public List<Practice> getAllPracticesForUser(int userId) {
        return repository.findAllPracticesByUserId(userId);
    }

    public Practice createPractice(Practice practice) {
        reservationService.validateReservationTimeIsFreeForCourt(practice.getReservation());
        TennisCourtReservation reservation = tennisCourtReservationRepository.create(practice.getReservation());
        return repository.addPractice(practice, reservation.getId());
    }
}
