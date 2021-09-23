package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.Stock;
import com.techelevator.model.Trade;

public interface TradeDao {

    void buyTrade(Trade trade, Player player);

    void sellTrade(Trade trade, Stock stock);

    boolean createTrade(Trade trade);

}
