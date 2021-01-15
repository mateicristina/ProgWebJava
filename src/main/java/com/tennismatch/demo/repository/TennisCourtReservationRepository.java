package com.tennismatch.demo.repository;

import com.tennismatch.demo.domain.TennisCourtReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class TennisCourtReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TennisCourtReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<TennisCourtReservation> findById(int id) {
        String sql = "SELECT * from tennis_court_reservations where id = ?";
        RowMapper<TennisCourtReservation> mapper = ((resultSet, i) ->
                new TennisCourtReservation(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId1"),
                        resultSet.getInt("userId2"),
                        resultSet.getInt("courtId"),
                        resultSet.getDate("date"),
                        resultSet.getTime("startHour"),
                        resultSet.getTime("endHour")
                ));

        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public TennisCourtReservation create(TennisCourtReservation reservation) {
        String sql = "INSERT INTO tennis_court_reservations values(?,?,?,?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, reservation.getUserId1());

            if (reservation.getUserId2() == null) {
                preparedStatement.setNull(3, Types.BIGINT);
            } else {
                preparedStatement.setNull(3, reservation.getUserId2());
            }

            preparedStatement.setInt(4, reservation.getCourtId());
            preparedStatement.setDate(5, new Date(reservation.getDate().getTime()));
            preparedStatement.setTime(6, reservation.getStartHour());
            preparedStatement.setTime(7, reservation.getEndHour());
            return preparedStatement;
        }, holder);
        reservation.setId(holder.getKey().intValue());
        return new TennisCourtReservation(reservation.getId(), reservation.getUserId1(), reservation.getUserId2(), reservation.getCourtId(), reservation.getDate(), reservation.getStartHour(), reservation.getEndHour());
    }

    public Optional<TennisCourtReservation> findByCourtIdAndDateAndTimeInterval(int id, Date date, Time startHour, Time endHour) {
        String sql = "SELECT * FROM tennis_court_reservations where courtId = ? and date = ? and" +
                "(? < startHour and (? > startHour and ? < endHour)" +
                "or (startHour <= ? and ? < endHour))";
        RowMapper<TennisCourtReservation> mapper = ((resultSet, i) ->
                new TennisCourtReservation(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId1"),
                        resultSet.getInt("userId2"),
                        resultSet.getInt("courtId"),
                        resultSet.getDate("date"),
                        resultSet.getTime("startHour"),
                        resultSet.getTime("endHour")
                ));

        return jdbcTemplate.query(sql, mapper, id, date, startHour, endHour, endHour, startHour, startHour).stream().findFirst();
    }

    public List<TennisCourtReservation> findAllByCourtId(int id) {
        String sql = "SELECT * from tennis_court_reservations where courtId = ?";
        RowMapper<TennisCourtReservation> mapper = ((resultSet, i) ->
                new TennisCourtReservation(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId1"),
                        resultSet.getInt("userId2"),
                        resultSet.getInt("courtId"),
                        resultSet.getDate("date"),
                        resultSet.getTime("startHour"),
                        resultSet.getTime("endHour")
                ));

        return jdbcTemplate.query(sql, mapper, id);
    }

    public void deleteById(int id) {
        String sql = "delete from tennis_court_reservations where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
