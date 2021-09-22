package com.techelevator;

import com.techelevator.services.RestStockInfoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static RestStockInfoService restStockInfoService = new RestStockInfoService();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        restStockInfoService.getStockInfo("AAPL");
    }

}
