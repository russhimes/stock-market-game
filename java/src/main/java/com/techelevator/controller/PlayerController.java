package com.techelevator.controller;

import com.techelevator.dao.PlayerDao;
import com.techelevator.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
