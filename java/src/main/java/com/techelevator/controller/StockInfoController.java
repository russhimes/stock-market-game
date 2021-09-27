package com.techelevator.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.techelevator.dao.PlayerDao;
import com.techelevator.model.Player;
import com.techelevator.model.SearchInfo;
import com.techelevator.model.StockInfo;
import com.techelevator.services.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (origins="*")
@EnableScheduling
public class StockInfoController {
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private PlayerDao playerDao;

    @RequestMapping(path="/stockinfo/{stockSymbol}", method= RequestMethod.GET)
    public StockInfo getStockInfo(@PathVariable String stockSymbol) {
        return stockInfoService.getStockInfo(stockSymbol);
    }

    @RequestMapping(path="/stockinfo/topten", method=RequestMethod.GET)
    public List<StockInfo> getTopTenStockInfo() {
        return stockInfoService.getTopStocks();
    }

    @RequestMapping(path="/stockinfo/search/{searchTerm}", method=RequestMethod.GET)
    public List<SearchInfo> getSearchInfo(@PathVariable String searchTerm) {
        return stockInfoService.getSearchInfo(searchTerm);
    }


    //I think this will run hourly based on the @Scheduled documentation
    //Optimization: If we have more than 30 stocks to update this will currently crash.
    //              Could ask it to wait a minute if we get a 400 error from finnhub.
   /* @Scheduled(cron = "0 0 * * * *")
    public void updateBalances() {
        List<Player> players = playerDao.getAllPlayers();
        int counter = 0;
        for (Player player : players) {
            stockInfoService.getPortfolioValue(player.getId());
        }
    }*/
}
