package com.techelevator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.techelevator.model.SearchInfo;
import com.techelevator.model.StockInfo;

import java.math.BigDecimal;
import java.util.List;

public interface StockInfoService {
    // returns top ten stocks in descending order from API
    List<StockInfo> getTopStocks();

    StockInfo getStockInfo(String stockSymbol);

    List<SearchInfo> getSearchInfo(String searchTerm);

    BigDecimal getPortfolioValue(int playerId);
}
