package com.tennismatch.demo.controller;

import com.tennismatch.demo.domain.Practice;
import com.tennismatch.demo.dto.PracticeDto;
import com.tennismatch.demo.mapper.PracticeMapper;
import com.tennismatch.demo.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("practice")
public class PracticeController {

    private PracticeMapper mapper;
    private PracticeService service;

    @Autowired
    public PracticeController(PracticeMapper mapper, PracticeService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(path = "/get-practices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PracticeDto>> getAllPractices(@Valid @RequestParam int userId) {
        List<Practice> practices = service.getAllPracticesForUser(userId);
        return new ResponseEntity<>(mapper.toDto(practices), HttpStatus.OK);
    }

    @PostMapping(path = "/add-practice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PracticeDto> createPractice(@Valid @RequestBody PracticeDto newPractice) {
        Practice practice = service.createPractice(mapper.toEntity(newPractice));
        return new ResponseEntity<>(mapper.toDto(practice), HttpStatus.CREATED);
    }
}
