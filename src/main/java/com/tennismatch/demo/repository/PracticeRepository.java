package com.tennismatch.demo.repository;

import com.tennismatch.demo.domain.Practice;
import com.tennismatch.demo.domain.TennisCourtReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PracticeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PracticeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Practice> findAllPracticesByUserId(int userId) {
        String sql = "SELECT * from practices as p join tennis_court_reservations t on p.courtReservationId = t.id where p.userId = ? ";
        RowMapper<Practice> mapper = ((resultSet, i) ->
                new Practice(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        new TennisCourtReservation(
                                resultSet.getInt("courtReservationId"),
                                resultSet.getInt("userId1"),
                                resultSet.getInt("userId2"),
                                resultSet.getInt("courtId"),
                                resultSet.getDate("date"),
                                resultSet.getTime("startHour"),
                                resultSet.getTime("endHour")
                        )
                ));

        return jdbcTemplate.query(sql, mapper, userId);
    }

    public Practice addPractice(Practice practice, int reservationId) {
        String sql = "INSERT into practices values(?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, practice.getUserId());
            preparedStatement.setInt(3, reservationId);
            return preparedStatement;
        }, holder);
        practice.setId(holder.getKey().intValue());
        practice.getReservation().setId(reservationId);
        return new Practice(practice.getId(), practice.getUserId(), practice.getReservation());
    }
}
