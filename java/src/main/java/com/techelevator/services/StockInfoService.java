package com.techelevator.services;

import com.techelevator.model.StockInfo;

import java.util.List;

public interface StockInfoService {
    // returns top ten stocks in descending order from API
    List<StockInfo> getTopStocks();

    StockInfo getStockInfo(String stockSymbol);
}
