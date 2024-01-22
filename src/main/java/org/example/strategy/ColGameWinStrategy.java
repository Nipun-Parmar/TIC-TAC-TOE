package org.example.strategy;

import org.example.model.Board;
import org.example.model.Move;
import org.example.model.Player;
import org.example.model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColGameWinStrategy implements GameWinningStrategy{
    private final Map<Integer, HashMap<Symbol,Integer>> colCountMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        // get the col
        // get the player who have moved now
        // create a hashmap <Symbol,Column>
        int col = move.getCell().getCol();
        Symbol playerSymbol = move.getPlayer().getSymbol();

        if(!colCountMap.containsKey(col)){
            colCountMap.put(col,new HashMap<>());
        }

        Map<Symbol,Integer> symbolMap = colCountMap.get(col);

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
        int col = move.getCell().getCol();
        Symbol playerSymbol = move.getPlayer().getSymbol();

        Map<Symbol,Integer> symbolMap = colCountMap.get(col);
        symbolMap.put(playerSymbol,symbolMap.get(playerSymbol)-1);
    }
}
