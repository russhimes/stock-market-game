package com.techelevator.services;

import com.techelevator.dao.*;
import com.techelevator.model.Game;
import com.techelevator.model.Player;
import com.techelevator.model.Stock;
import com.techelevator.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EndGameService implements Runnable  {

    private Game game;
    private String gameWinner;
    private List<Player> players = new ArrayList<>();
    private TaskScheduler taskScheduler =  new ThreadPoolTaskScheduler();

    @Autowired
    private UserDao userDao;

    @Autowired
    private GameDao gameDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private StockInfoService stockInfoService;




    public EndGameService(int gameId) {
        this.game = gameDao.getGameById(gameId);
    }

    @Override
    public void run() {
        players = playerDao.getPlayersByGame(game.getId());
        for (Player player : players) {
            List<Stock> stocks = stockDao.getStocksByPlayerId(player.getId());
            for (Stock stock : stocks) {
                Trade trade = new Trade(stock.getId(), stock.getTotal_shares(), "sell",
                        stockInfoService.getStockInfo(stock.getStock_ticker()).getCurrentPrice(), "shares",
                        LocalDate.now(), LocalTime.now());
                stock.setTotal_shares(stock.getTotal_shares() -trade.getShares_traded());
                player.setAvailable_funds(player.getAvailableFunds().
                        add(trade.getPrice().
                                multiply(new BigDecimal(trade.getShares_traded()).setScale(2, RoundingMode.HALF_UP))));
                playerDao.updatePlayer(player);
                stockDao.updateStock(stock);
                tradeDao.createTrade(trade);
            }
        }
        players.sort(Comparator.comparing(Player::getAvailableFunds).reversed());
        gameWinner = userDao.getUserById((long) players.get(0).getUser_id()).getUsername();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(String gameWinner) {
        this.gameWinner = gameWinner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
