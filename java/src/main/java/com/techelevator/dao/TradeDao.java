package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.Stock;
import com.techelevator.model.Trade;

import java.util.List;

public interface TradeDao {

    void buyTrade(Trade trade, Player player);

    void sellTrade(Trade trade, Stock stock);

    void createTrade(Trade trade);

    public List<Trade> tradeHistory(int playerId);

}
