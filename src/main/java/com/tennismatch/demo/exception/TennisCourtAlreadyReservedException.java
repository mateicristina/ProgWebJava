package com.tennismatch.demo.exception;

public class TennisCourtAlreadyReservedException extends RuntimeException {

    public TennisCourtAlreadyReservedException() {
        super("Tennis court is already reserved!");
    }
}
