package com.tennismatch.demo.repository;

import com.tennismatch.demo.domain.TennisBase;
import com.tennismatch.demo.domain.TennisCourt;
import com.tennismatch.demo.domain.TennisCourtListReservations;
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
public class TennisBaseRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TennisBaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TennisBase> findAll() {
        String sql = "SELECT * from tennis_bases";
        RowMapper<TennisBase> mapper = ((resultSet, i) ->
                new TennisBase(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")
                ));

        return jdbcTemplate.query(sql, mapper);
    }


    public TennisBase add(TennisBase tennisBase) {
        String sql = "INSERT into tennis_bases value(?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, tennisBase.getName());
            preparedStatement.setString(3, tennisBase.getAddress());
            return preparedStatement;
        }, holder);

        tennisBase.setId(holder.getKey().intValue());
        return new TennisBase(tennisBase.getId(), tennisBase.getName(), tennisBase.getAddress());
    }
}
