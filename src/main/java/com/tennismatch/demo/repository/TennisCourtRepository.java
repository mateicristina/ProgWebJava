package com.tennismatch.demo.repository;

import com.tennismatch.demo.domain.TennisCourt;
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
public class TennisCourtRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TennisCourtRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TennisCourt> findAllCourts(int tennisBaseId) {
        String sql = "SELECT * from tennis_courts where baseId = ?";
        RowMapper<TennisCourt> mapper = ((resultSet, i) ->
                new TennisCourt(
                        resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getInt("baseId")
                ));

        return jdbcTemplate.query(sql, mapper, tennisBaseId);
    }

    public TennisCourt create(int baseId, int courtNumber) {
        String sql = "insert into tennis_courts values(?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, courtNumber);
            preparedStatement.setInt(3, baseId);
            return preparedStatement;
        }, holder);
        return new TennisCourt(holder.getKey().intValue(), courtNumber, baseId);
    }
}
