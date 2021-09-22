package com.techelevator.dao;

import com.techelevator.model.Stock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStockDao implements StockDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcStockDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public List<Stock> getStocksByPlayerId(int id) {
        String sql = "SELECT id, player_id, stock_name, stock_ticker, total_shares " +
                "FROM stocks " +
                "WHERE player_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        List<Stock> stocks = new ArrayList<>();
        while(results.next()) {
            stocks.add(mapResultToStock(results));
        }
        return stocks;
    }

    private Stock mapResultToStock(SqlRowSet result) {
        int id = result.getInt("id");
        int player_id = result.getInt("player_id");
        String stock_name = result.getString("stock_name");
        String stock_ticker = result.getString("stock_ticker");
        double total_shares = result.getDouble("total_shares");

        Stock stock = new Stock(player_id, stock_name, stock_ticker, total_shares);
        stock.setId(id);
        return stock;
    }
}
