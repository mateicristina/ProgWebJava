package com.tennismatch.demo.service;

import com.tennismatch.demo.domain.TennisBase;
import com.tennismatch.demo.domain.TennisCourt;
import com.tennismatch.demo.domain.TennisCourtListReservations;
import com.tennismatch.demo.domain.TennisCourtReservation;
import com.tennismatch.demo.repository.TennisBaseRepository;
import com.tennismatch.demo.repository.TennisCourtRepository;
import com.tennismatch.demo.repository.TennisCourtReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TennisBaseService {

    private final TennisBaseRepository tennisBaseRepository;
    private final TennisCourtReservationRepository tennisCourtReservationRepository;
    private final TennisCourtRepository tennisCourtRepository;

    @Autowired
    public TennisBaseService(
            TennisBaseRepository tennisBaseRepository,
            TennisCourtReservationRepository tennisCourtReservationRepository,
            TennisCourtRepository tennisCourtRepository
    ) {
        this.tennisBaseRepository = tennisBaseRepository;
        this.tennisCourtReservationRepository = tennisCourtReservationRepository;
        this.tennisCourtRepository = tennisCourtRepository;
    }

    public List<TennisBase> getAllTennisBases() {
        return tennisBaseRepository.findAll();
    }

    public List<TennisCourtListReservations> getCourtsWithReservations(int tennisBaseId) {
        List<TennisCourt> tennisCourts = tennisCourtRepository.findAllCourts(tennisBaseId);
        List<TennisCourtListReservations> tennisCourtListReservations = new ArrayList<>();
        for (int i = 0; i < tennisCourts.size(); i++ ) {
            List<TennisCourtReservation> tennisCourtReservations = tennisCourtReservationRepository.findAllByCourtId(tennisCourts.get(i).getId());
            tennisCourtListReservations.add(
                    new TennisCourtListReservations(
                            tennisCourts.get(i).getNumber(),
                            tennisCourtReservations
                    )
            );
        }
        return tennisCourtListReservations;
    }

    public TennisBase create(TennisBase tennisBase) {
        return tennisBaseRepository.add(tennisBase);
    }

    public TennisCourt addCourt(int baseId, int courtNumber) {
        return tennisCourtRepository.create(baseId, courtNumber);
    }
}
