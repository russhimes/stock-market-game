package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GameController {

    @Autowired
    private GameDao gameDao;

    @RequestMapping(path="/games", method = RequestMethod.GET)
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @RequestMapping(path="/games/organizer/{organizer_id}", method = RequestMethod.GET)
    public List<Game> getGamesByOrganizerId(@PathVariable int organizer_id) { return gameDao.getGamesByOrganizerId(organizer_id); }

    @RequestMapping(path="/games/player/{user_id}", method = RequestMethod.GET)
    public List<Game> getGameByUserId(@PathVariable int user_id) { return gameDao.getGameByUserId(user_id); }

    @RequestMapping(path="/games/{id}", method = RequestMethod.GET)
    public Game getGameById(@PathVariable int id) { return gameDao.getGameById(id); }

    @RequestMapping(path="/games", method = RequestMethod.POST)
    public void createGame(@RequestBody Game game) { gameDao.createGame(game); }

    @RequestMapping(path="/games/{id}", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable int id) { gameDao.deleteGame(id); }
}
