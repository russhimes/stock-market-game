package com.techelevator.dao;

import com.techelevator.model.Stock;

import java.util.List;

public interface StockDao {
    List<Stock> getStocksByPlayerId(int id);
}
