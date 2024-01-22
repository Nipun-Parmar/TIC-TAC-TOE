package org.example.controller;

import org.example.model.Game;
import org.example.model.Player;
import org.example.model.Types.GameState;
import org.example.strategy.GameWinningStrategy;

import java.util.List;

public class GameController {
    public void printBoard(Game game){
        game.printBoard();
    }
    public Player getWinner(Game game){
        return game.getWinnerPLayer(game);
    }
    public void undo(Game game)throws Exception{
        game.undo();
    }
    public void makeMove(Game game) throws Exception {
        game.makeMove();
    }
    public GameState checkState(Game game){
        return game.getGameState();
    }
    public Game startGame(List<Player> players,
                          List<GameWinningStrategy> winRules,
                          Integer size) throws Exception {
        return Game.getBuilder()
                .setDimension(size)
                .setPlayers(players)
                .setWinningStrategies(winRules)
                .build();
    }

}
