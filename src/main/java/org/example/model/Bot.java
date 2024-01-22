package org.example.model;

import org.example.model.Types.BotDifficultyLevel;
import org.example.model.Types.PlayerType;

import java.util.Scanner;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, Integer id, Symbol symbol, PlayerType playerType,
               BotDifficultyLevel botDifficultyLevel, Scanner sc) {
        super(name, id, symbol, playerType,sc); // PLayer
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }
}
