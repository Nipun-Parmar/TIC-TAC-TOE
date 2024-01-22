package org.example.strategy;

import org.example.model.Board;
import org.example.model.Move;
import org.example.model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowGameWinStrategy implements GameWinningStrategy{
    private final Map<Integer, HashMap<Symbol,Integer>> rowCountMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        Symbol playerSymbol = move.getPlayer().getSymbol();

        if(!rowCountMap.containsKey(row)){
            rowCountMap.put(row,new HashMap<>());
        }

        Map<Symbol,Integer> symbolMap = rowCountMap.get(row);

        if(!symbolMap.containsKey(playerSymbol)){
            symbolMap.put(playerSymbol,0);
        }
        symbolMap.put(playerSymbol,symbolMap.get(playerSymbol)+1);

        if(symbolMap.get(playerSymbol).equals(board.getSize())){
            return true;
        }

        return false;
    }

    @Override
    public void handeUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol playerSymbol = move.getPlayer().getSymbol();

        Map<Symbol,Integer> symbolMap = rowCountMap.get(row);
        symbolMap.put(playerSymbol,symbolMap.get(playerSymbol)-1);
    }
}
