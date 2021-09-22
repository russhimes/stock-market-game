package com.techelevator.controller;

import com.techelevator.model.StockInfo;
import com.techelevator.services.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class StockInfoController {
    @Autowired
    private StockInfoService stockInfoService;

    @RequestMapping(path="/stockinfo/{stockSymbol}", method= RequestMethod.GET)
    public StockInfo getStockInfo(@PathVariable String stockSymbol) {
        return stockInfoService.getStockInfo(stockSymbol);
    }
}
