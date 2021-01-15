package com.tennismatch.demo.controller;

import com.tennismatch.demo.dto.ErrorResponse;
import com.tennismatch.demo.exception.BadRequestException;
import com.tennismatch.demo.exception.EntityNotFoundException;
import com.tennismatch.demo.exception.TennisCourtAlreadyReservedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(404)
                        .message(ex.getMessage())
                        .build()
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleTennisCourtAlreadyReservedException(TennisCourtAlreadyReservedException ex) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }
}