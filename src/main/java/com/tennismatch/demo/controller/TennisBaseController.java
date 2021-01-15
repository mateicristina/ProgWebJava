package com.tennismatch.demo.controller;

import com.tennismatch.demo.domain.TennisBase;
import com.tennismatch.demo.domain.TennisCourt;
import com.tennismatch.demo.domain.TennisCourtListReservations;
import com.tennismatch.demo.dto.TennisBaseDto;
import com.tennismatch.demo.dto.TennisCourtDto;
import com.tennismatch.demo.dto.TennisCourtListReservationsDto;
import com.tennismatch.demo.mapper.TennisBaseMapper;
import com.tennismatch.demo.mapper.TennisCourtListReservationsMapper;
import com.tennismatch.demo.mapper.TennisCourtMapper;
import com.tennismatch.demo.service.TennisBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("tennis-base")
public class TennisBaseController {

    private TennisBaseService service;
    private TennisBaseMapper tennisBaseMapper;
    private TennisCourtMapper tennisCourtMapper;
    private TennisCourtListReservationsMapper tennisCourtListReservationsMapper;

    @Autowired
    public TennisBaseController(
            TennisBaseService service,
            TennisBaseMapper tennisBaseMapper,
            TennisCourtListReservationsMapper tennisCourtListReservationsMapper,
            TennisCourtMapper tennisCourtMapper
    ) {
        this.service = service;
        this.tennisBaseMapper = tennisBaseMapper;
        this.tennisCourtMapper = tennisCourtMapper;
        this.tennisCourtListReservationsMapper = tennisCourtListReservationsMapper;
    }

    @GetMapping(path = "/get-all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TennisBaseDto>> getAllTennisBases() {
        List<TennisBase> tennisBases = service.getAllTennisBases();
        return new ResponseEntity<>(tennisBaseMapper.toDto(tennisBases), HttpStatus.OK);
    }

    @GetMapping(path = "/get-court-reservations/{tennisBaseId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TennisCourtListReservationsDto>> getAllCourtsReservations(@Valid @PathVariable int tennisBaseId) {
        List<TennisCourtListReservations> listCourts = service.getCourtsWithReservations(tennisBaseId);
        return new ResponseEntity<>(tennisCourtListReservationsMapper.toDto(listCourts), HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TennisBaseDto> createTennisBase(@Valid @RequestBody TennisBaseDto request) {
        TennisBase saved = service.create(tennisBaseMapper.toEntity(request));
        return new ResponseEntity<>(tennisBaseMapper.toDto(saved), HttpStatus.CREATED);
    }

    @PostMapping(path = "/add-court/{baseId}/{courtNumber}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TennisCourtDto> addCourt(@Valid @PathVariable int baseId, @PathVariable int courtNumber) {
        TennisCourt saved = service.addCourt(baseId, courtNumber);
        return new ResponseEntity<>(tennisCourtMapper.toDto(saved), HttpStatus.CREATED);
    }
}
