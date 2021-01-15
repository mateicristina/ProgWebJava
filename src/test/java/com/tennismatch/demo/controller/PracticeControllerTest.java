package com.tennismatch.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennismatch.demo.domain.Practice;
import com.tennismatch.demo.domain.TennisCourtReservation;
import com.tennismatch.demo.dto.PracticeDto;
import com.tennismatch.demo.mapper.PracticeMapper;
import com.tennismatch.demo.service.PracticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PracticeController.class)
public class PracticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PracticeService practiceService;

    @MockBean
    private PracticeMapper practiceMapper;

    @Test
    void test_createPractice() throws Exception {
        // Arrange
        PracticeDto request = PracticeDto.builder()
                .reservation(
                        TennisCourtReservation.builder()
                                .courtId(1)
                                .date(new Date(2021, 10, 10))
                                .startHour(new Time(120000))
                                .endHour(new Time(140000))
                                .build()
                )
                .userId(1)
                .build();

        Practice created = Practice.builder()
                .id(1)
                .reservation(
                        TennisCourtReservation.builder()
                                .courtId(1)
                                .date(new Date(2021, 10, 10))
                                .startHour(new Time(120000))
                                .endHour(new Time(140000))
                                .build()
                )
                .userId(1)
                .build();

        when(practiceService.createPractice(any(Practice.class))).thenReturn(created);

        // Act
        mockMvc.perform(post("/practice/add-practice")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Assert
    }

//    @Test
//    void test_getAllPractices() throws Exception {
//        // Arrange
//        Practice request1 = Practice.builder()
//                .reservation(
//                        TennisCourtReservation.builder()
//                                .courtId(1)
//                                .userId1(1)
//                                .date(new Date(2021, 10, 10))
//                                .startHour(new Time(120000))
//                                .endHour(new Time(140000))
//                                .build()
//                )
//                .userId(1)
//                .build();
//
//        Practice request2 = Practice.builder()
//                .reservation(
//                        TennisCourtReservation.builder()
//                                .courtId(2)
//                                .date(new Date(2021, 10, 11))
//                                .startHour(new Time(120000))
//                                .endHour(new Time(140000))
//                                .build()
//                )
//                .userId(1)
//                .build();
//
//        List<Practice> practices = new ArrayList<>();
//        practices.add(practiceService.createPractice(request1));
//        practices.add(practiceService.createPractice(request2));
//
//        // Act
//        mockMvc.perform(get("/practice/get-practices?userId=1")
//                .contentType("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].courtId", is(1)))
//                .andExpect(jsonPath("$[1].courtId", is(2)))
//        ;
//
//        // Assert
//    }

//    @Test
//    void test() throws Exception {
//        // Arrange
//        // Act
//        // Assert
//    }
}
