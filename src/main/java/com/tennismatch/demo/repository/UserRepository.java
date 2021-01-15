package com.tennismatch.demo.repository;

import com.tennismatch.demo.domain.NewUser;
import com.tennismatch.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * from users where email = ? and password = ?";
        RowMapper<User> mapper = ((resultSet, i) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email")
                ));

        return jdbcTemplate.query(sql, mapper, email, password).stream().findFirst();
    }

    public User save(NewUser user) {
        String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPassword());
            return preparedStatement;
        }, holder);
        user.setId(holder.getKey().intValue());
        return new User(user.getId(), user.getName(), user.getAge(), user.getCity(), user.getPhoneNumber(), user.getEmail());
    }

    public Optional<User> findById(int id) {
        String sql = "SELECT * from users WHERE id = ?";
        RowMapper<User> mapper = ((resultSet, i) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email")
                ));

        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public Optional<User> findByName(String name) {
        String sql = "SELECT * from users WHERE name = ?";
        RowMapper<User> mapper = ((resultSet, i) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email")
                ));

        return jdbcTemplate.query(sql, mapper, name).stream().findFirst();
    }

    public User updateUser(User user) {
        String sql = "UPDATE users set name = ?, age = ?, city = ?, phoneNumber = ? where id = ?";
        RowMapper<User> mapper = ((resultSet, i) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email")
                ));

        jdbcTemplate.update(sql,
                user.getName(),
                user.getAge(),
                user.getCity(),
                user.getPhoneNumber(),
                user.getId()
        );
        return user;
    }

    public void deleteAll() {
        String sql = "DELETE FROM users";
        jdbcTemplate.update(sql);
    }

    public int countProducts() {
        String sql = "select count(*) from users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}