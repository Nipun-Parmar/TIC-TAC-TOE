package org.example.model;

import org.example.model.Types.CellState;

public class Cell {
    private Integer row;
    private Integer col;
    private CellState cellState;
    private Player player;

    public Cell(Integer row, Integer col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }
    public void display(){
        if(cellState.equals(CellState.EMPTY)){
            System.out.print("| - |");
        }
        else{
            System.out.print("| " + this.getPlayer().getSymbol().getPlayerSymbol() +
                    " |");
        }
    }
    public Integer getRow(){
        return row;
    }
    public Integer getCol(){
        return col;
    }

    public CellState getCellState() {
        return cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
