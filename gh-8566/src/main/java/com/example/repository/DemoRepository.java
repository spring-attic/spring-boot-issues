package com.example.repository;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {

    private final JdbcOperations jdbcOperations;

    DemoRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public int findNumber() {
        return jdbcOperations.queryForObject("SELECT 1",int.class);
    }

}
