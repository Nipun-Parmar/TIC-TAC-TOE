package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    private Integer size;
    public Board(int dimension){
        board = new ArrayList<>();
        for(int i = 0; i < dimension; i++){
            board.add(new ArrayList<>());
            for(int j = 0; j < dimension; j++){
                board.get(i).add(new Cell(i,j));
            }
        }
    }
    public List<List<Cell>> getBoard(){ return board;}
    public Integer getSize(){return this.board.size();}

    public void printBoard() {
        for(List<Cell> row : board){
            for(Cell cell : row){
                cell.display();
            }
            System.out.println("\n");
        }
    }
}
