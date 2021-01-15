package com.tennismatch.demo.controller;

import com.tennismatch.demo.domain.MatchInvitation;
import com.tennismatch.demo.dto.MatchInvitationDto;
import com.tennismatch.demo.mapper.MatchInvitationMapper;
import com.tennismatch.demo.service.TennisMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("tennis-match")
public class TennisMatchController {

    private TennisMatchService tennisMatchService;
    private MatchInvitationMapper matchInvitationMapper;

    @Autowired
    public TennisMatchController(TennisMatchService tennisMatchService, MatchInvitationMapper matchInvitationMapper) {
        this.tennisMatchService = tennisMatchService;
        this.matchInvitationMapper = matchInvitationMapper;
    }

    @PostMapping(path = "/create-invitation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MatchInvitationDto> createInvitation(@Valid @RequestBody MatchInvitationDto matchInvitation) {
        MatchInvitation matchInvitationCreated = tennisMatchService.createInvitation(matchInvitationMapper.toEntity(matchInvitation));
        return new ResponseEntity<>(matchInvitationMapper.toDto(matchInvitationCreated), HttpStatus.CREATED);
    }

    @GetMapping(path = "/get-invitations/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MatchInvitationDto>> getInvitations(@Valid @PathVariable int id) {
        List<MatchInvitation> matchInvitations = tennisMatchService.getInvitations(id);
        return new ResponseEntity<>(matchInvitationMapper.toDto(matchInvitations), HttpStatus.OK);
    }

    @PostMapping(path = "/accept-invitation/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> acceptInvitation(@Valid @PathVariable int id) {
        tennisMatchService.acceptInvitation(id);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @PostMapping(path = "/refuse-invitation/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> refuseInvitation(@Valid @PathVariable int id) {
        tennisMatchService.refuseInvitation(id);
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}
