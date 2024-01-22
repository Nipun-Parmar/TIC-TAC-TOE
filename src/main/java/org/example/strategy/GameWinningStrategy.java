package org.example.strategy;

import org.example.model.Board;
import org.example.model.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);
    void handeUndo(Board board, Move move);
}
