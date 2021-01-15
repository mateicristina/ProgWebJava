package com.tennismatch.demo.repository;

import com.tennismatch.demo.domain.MatchInvitation;
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
import java.util.Optional;

@Repository
public class TennisMatchInvitationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TennisMatchInvitationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MatchInvitation save(MatchInvitation matchInvitation, int reservationId) {
        String sql = "INSERT INTO tennis_match_pending_invitations values(?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, matchInvitation.getSenderUserId());
            preparedStatement.setInt(3, matchInvitation.getReceiverUserId());
            preparedStatement.setInt(4, reservationId);
            return preparedStatement;
        }, holder);
        matchInvitation.setId(holder.getKey().intValue());
        matchInvitation.getReservation().setId(reservationId);
        return new MatchInvitation(matchInvitation.getId(), matchInvitation.getSenderUserId(), matchInvitation.getReceiverUserId(), matchInvitation.getReservation());
    }

    public List<MatchInvitation> finaAllByUserId(int id) {
        String sql = "SELECT * from tennis_match_pending_invitations p " +
                "join tennis_court_reservations t on p.reservationId = t.id " +
                "where receiverUserId = ?";
        RowMapper<MatchInvitation> mapper = ((resultSet, i) ->
                new MatchInvitation(
                        resultSet.getInt("id"),
                        resultSet.getInt("senderUserId"),
                        resultSet.getInt("receiverUserId"),
                        new TennisCourtReservation(
                                resultSet.getInt("reservationId"),
                                resultSet.getInt("userId1"),
                                resultSet.getInt("userId2"),
                                resultSet.getInt("courtId"),
                                resultSet.getDate("date"),
                                resultSet.getTime("startHour"),
                                resultSet.getTime("endHour")
                        )
                ));

        return jdbcTemplate.query(sql, mapper, id);
    }

    public void deleteById(int id) {
        String sql = "DELETE from tennis_match_pending_invitations where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<MatchInvitation> getById(int id) {
        return finaAllByUserId(id).stream().findFirst();
    }
}
