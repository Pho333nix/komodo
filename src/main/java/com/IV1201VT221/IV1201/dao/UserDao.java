package com.IV1201VT221.IV1201.dao;

import com.IV1201VT221.IV1201.model.User;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class UserDao implements UserDaoInterface {

    private final JdbcTemplate jdbctemplate;

    @Autowired
    public UserDao(JdbcTemplate asd){
        this.jdbctemplate = asd;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(UUID id, User user) {
        return 0;
    }

    @Override
    public int deleteUser(UUID id, User user) {
        return 0;
    }
}
