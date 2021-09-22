package com.techelevator.controller;

import com.techelevator.dao.TradeDao;
import com.techelevator.model.Player;
import com.techelevator.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TradeController {

    @Autowired
    private TradeDao tradeDao;

    @RequestMapping(path="/trades", method = RequestMethod.POST)
    public void buyTrade(@RequestBody Trade trade, @RequestBody Player player) {
        tradeDao.buyTrade(trade, player);
    }

}
