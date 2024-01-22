package org.example.model;

import org.example.model.Types.PlayerType;

import java.util.Scanner;

public class Player {
    private String name;
    private Integer id;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(String name, Integer id, Symbol symbol, PlayerType playerType,Scanner scanner) {
        this.name = name;
        this.id = id;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = scanner;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Move makeMove() {
        System.out.println("Please tell row count where you want to move (Starting from 0) : ");
        int row = scanner.nextInt();

        System.out.println("Please tell col count where you want to move (Starting from 0) : ");
        int col = scanner.nextInt();

        return new Move(new Cell(row,col), this);
    }
}
