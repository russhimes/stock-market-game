package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.StockInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Component
public class RestStockInfoService implements StockInfoService {

    private String apiKey = "c55kruaad3icdhg123f0";
    private String BASE_URL = "https://finnhub.io/api/v1/";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<StockInfo> getTopStocks() {
        //TODO
        return null;
    }

    @Override
    public StockInfo getStockInfo(String stockSymbol) {
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        String url = BASE_URL + "/quote?symbol=" + stockSymbol + "&token=" + apiKey;
        ResponseEntity<String> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        StockInfo stockInfo = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(result.getBody());
            JsonNode root = jsonNode.path("data");
            String price = jsonNode.path("c").asText();
            stockInfo = new StockInfo(stockSymbol, new BigDecimal(price));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return stockInfo;
    }

}
