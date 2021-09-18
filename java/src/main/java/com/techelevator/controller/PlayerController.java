package com.techelevator.controller;

import com.techelevator.dao.PlayerDao;
import com.techelevator.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PlayerController {

    @Autowired
    private PlayerDao playerDao;

    @RequestMapping(path="/players", method = RequestMethod.GET)
    public List<Player> getAllPlayers() {
        return playerDao.getAllPlayers();
    }

    @RequestMapping(path="/players/game/{game_id}", method = RequestMethod.GET)
    public List<Player> getPlayersByGameId(@PathVariable int game_id) {
        return playerDao.getPlayersByGame(game_id);
    }

    @RequestMapping(path="/players/{id}", method=RequestMethod.GET)
    public Player getPlayerById(@PathVariable int id) {
        return playerDao.getPlayerById(id);
    }

    @RequestMapping(path="/players", method= RequestMethod.POST)
    public void createPlayer(@RequestBody Player player) {
        playerDao.createPlayer(player);
    }

    @RequestMapping(path="/players/{id}", method=RequestMethod.PUT)
    public void updatePlayer(@RequestBody Player player) {
        playerDao.updatePlayer(player);
    }

    @RequestMapping(path="/players/{id}", method=RequestMethod.DELETE)
    public void deletePlayer(@PathVariable int id) {
        playerDao.deletePlayer(id);
    }

}
