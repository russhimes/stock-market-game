package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.Trade;

public interface TradeDao {

    void buyOrSellTrade(Trade trade, Player player);

}
