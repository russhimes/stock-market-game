package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcStockDao implements StockDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcStockDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }
}
