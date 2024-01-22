package org.example;

import org.example.controller.GameController;
import org.example.model.Game;
import org.example.model.Player;
import org.example.model.Symbol;
import org.example.model.Types.GameState;
import org.example.model.Types.PlayerType;
import org.example.strategy.ColGameWinStrategy;
import org.example.strategy.GameWinningStrategy;
import org.example.strategy.RowGameWinStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();

        Scanner sc = new Scanner(System.in);
        // In a situation of multiple games running
        // our game controller must know about which game are we talking about
        // so we need to pass game object into the gameController

        //Game game = new Game();
        // but we cannot ask user to create a game object.

        //S1:
        List<Player> players = new ArrayList<>();
        players.add(new Player("Nipun",1,new Symbol('X'), PlayerType.HUMAN,sc));
        players.add(new Player("Mansi",2,new Symbol('0'), PlayerType.HUMAN,sc));

        List<GameWinningStrategy> winRules = new ArrayList<>();
        winRules.add(new RowGameWinStrategy());
        winRules.add(new ColGameWinStrategy());

        Game game = gameController.startGame(players, winRules, 3);

        //S2:
        while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            /*
             * 1. Print Board
             * 2. Identify whose Player's move it is
             * 3. Ask player to give cell no.to move
             */
            gameController.printBoard(game);

            System.out.println("Does anyone wants to undo? (y/n) : ");
            String undoAnswer = sc.next();

            if(undoAnswer.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }

            gameController.makeMove(game);
            // user->Controller->Game->PLayer
        }

        if(game.getGameState().equals(GameState.DRAW)){
            System.out.println("Game is Draw...");
        }

        if (game.getGameState().equals(GameState.END)){
            System.out.println("Game Ended and Player"
                    + gameController.getWinner(game).getName()
                    + "won the game...");
        }
    }
}