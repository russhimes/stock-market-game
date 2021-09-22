package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcTradeDao implements TradeDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTradeDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

}
