package com.techelevator.services;

import com.techelevator.model.StockInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestStockInfoService implements StockInfoService {
    @Override
    public List<StockInfo> getTopStocks() {
        //TODO 
        return null;
    }
}
