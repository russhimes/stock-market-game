package com.techelevator.controller;

import com.techelevator.model.StockInfo;
import com.techelevator.services.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StockInfoController {
    @Autowired
    private StockInfoService stockInfoService;

    @RequestMapping(path="/stockinfo/{stockSymbol}", method= RequestMethod.GET)
    public StockInfo getStockInfo(@PathVariable String stockSymbol) {
        return stockInfoService.getStockInfo(stockSymbol);
    }

    @RequestMapping(path="/stockinfo/topten", method=RequestMethod.GET)
    public List<StockInfo> getTopTenStockInfo() {
        return stockInfoService.getTopStocks();
    }
}
