package com.techelevator.controller;

import com.techelevator.dao.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StockController {

    @Autowired
    private StockDao stockDao;
}
