package com.techelevator.controller;

import com.techelevator.services.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StockInfoController {
    @Autowired
    private StockInfoService stockInfoService;


}
