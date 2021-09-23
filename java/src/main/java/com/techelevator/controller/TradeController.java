package com.techelevator.controller;

import com.techelevator.dao.PlayerDao;
import com.techelevator.dao.StockDao;
import com.techelevator.dao.TradeDao;
import com.techelevator.model.Player;
import com.techelevator.model.Stock;
import com.techelevator.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin
public class TradeController {

    @Autowired
    private TradeDao tradeDao;
    @Autowired
    private StockDao stockDao;
    @Autowired
    private PlayerDao playerDao;

    @RequestMapping(path="/trade/buy", method = RequestMethod.POST)
    public void buyTrade(@RequestBody Trade trade, @RequestBody Player player) {
        tradeDao.buyTrade(trade, player);
    }

    @RequestMapping(path="/trade/sell", method = RequestMethod.POST)
    public void sellTrade(@RequestBody Trade trade, @RequestBody Stock stock) {
        tradeDao.sellTrade(trade, stock);
    }

    @RequestMapping(path = "/trade", method = RequestMethod.POST)
    public void trade(@RequestBody Trade trade) {

        Stock stock =  stockDao.getStockByStockId(trade.getStock_id());
        Player player = playerDao.getPlayerById(stock.getPlayer_id());
        // make sure transaction is a buy --> they have enough $
        // if sell make sure they have enough stocks to sell
        if (trade.getBuy_or_sell().equalsIgnoreCase("buy")
                && (player.getAvailableFunds().compareTo(trade.getPrice()) == 1 || player.getAvailableFunds().compareTo(trade.getPrice()) == 0)){
            tradeDao.createTrade(trade);

            // update player's balance
            BigDecimal newBalance = player.getAvailableFunds().subtract(trade.getPrice());
            player.setAvailable_funds(newBalance);
            playerDao.updatePlayer(player);

            // update stock to reflect new number of shares
            double originalShares = stock.getTotal_shares();
            double newShares = trade.getShares_traded();
            stock.setTotal_shares(originalShares + newShares);
            stockDao.updateStock(stock);
        }

        // TODO validate sell transaction

    }

}
