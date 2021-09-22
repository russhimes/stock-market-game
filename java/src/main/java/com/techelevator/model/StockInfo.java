package com.techelevator.model;

import java.math.BigDecimal;

public class StockInfo {
    // TODO: create model for the stock info we want to get from the external API
    private String stockSymbol;
    private BigDecimal currentPrice;

    public StockInfo(String stockSymbol, BigDecimal currentPrice) {
        this.stockSymbol = stockSymbol;
        this.currentPrice = currentPrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
