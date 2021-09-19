package com.techelevator.dao;

import com.techelevator.model.Game;

import java.util.List;

public interface GameDao {

    List<Game> getAllGames();

    List<Game> getGamesByOrganizerId(int organizer_id);

    Game getGameById(int id);

    void createGame(Game game);

    void deleteGame(int id);

}
