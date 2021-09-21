package com.techelevator.dao;

import com.techelevator.model.Game;

import java.util.List;

public interface GameDao {

    List<Game> getAllGames();

    List<Game> getGamesByOrganizerId(int organizer_id);

    List<Game> getGameByUserId(int user_id);

    Game getGameById(int id);

    int createGame(Game game);

    void deleteGame(int id);

}
