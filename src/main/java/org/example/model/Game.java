package org.example.model;

import org.example.model.Types.CellState;
import org.example.model.Types.GameState;
import org.example.model.Types.PlayerType;
import org.example.strategy.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> playerMoves;
    private Player winnerPLayer;
    private GameState gameState;
    private Integer nextPlayerIndex;
    List<GameWinningStrategy> winningStrategies; // let us consider rules are given by user

    private Game(Integer dimension, List<Player> players,
                 List<GameWinningStrategy> winningStrategies) {
        this.players = players;
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
        this.playerMoves = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getPlayerMoves() {
        return playerMoves;
    }

    public Player getWinnerPLayer(Game game) {
        return winnerPLayer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Integer getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    /*
    * Define Builder for Game
    * */

    public static Builder getBuilder(){
        return new Builder();
    }

    public void makeMove() throws Exception {
        /**
         * S1. get the nextPLayerIndex
         * S2. call makeMove on PLayer
         * S3. Validation
         * S4. update your cell
         * S5. Add Move to list
         * S6. checkWinner
         */
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("Current PLayer's Move: " + currentPlayer.getName());
        Move move = currentPlayer.makeMove();
        if(!validateMove(move)){
            throw new Exception();
        }

        Integer row = move.getCell().getRow();
        Integer col = move.getCell().getCol();

        Cell celltobeUpdated = board.getBoard().get(row).get(col);
        celltobeUpdated.setCellState(CellState.FILLED);
        celltobeUpdated.setPlayer(move.getPlayer());

        move.setCell(celltobeUpdated);
        playerMoves.add(move);

        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % players.size();

        //checkWinner
        if(checkWinner(move,board)){
            gameState = GameState.END;
            winnerPLayer = move.getPlayer();
        }else if(playerMoves.size() == board.getSize()* board.getSize()){
            gameState = GameState.DRAW;
        }

    }

    public boolean checkWinner(Move move, Board board){
        for(GameWinningStrategy st : winningStrategies){
            if(st.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }
    private boolean validateMove(Move move) {
        /**
         *
         */
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getSize()){
            return false;
        }
        if(col >= board.getBoard().get(0).size()){
            return false;
        }

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return true;
        }

        return false;
    }
    // Print the board
    public void printBoard() {
        board.printBoard();
    }

    public void undo() throws Exception{
        if(playerMoves.size() == 0){
            throw new Exception();
        }
        /**
         * S1: remove the last move
         * S2: update cell state
         * S3: update hashmaps
         * S4: update player turn
         */

        Move lastMove = getPlayerMoves().get(playerMoves.size()-1);
        playerMoves.remove(lastMove);

        Cell lastCell = lastMove.getCell();
        lastCell.setCellState(CellState.EMPTY);
        lastCell.setPlayer(null);

        for(GameWinningStrategy st : winningStrategies){
            st.handeUndo(board,lastMove);
        }

        nextPlayerIndex--;
        nextPlayerIndex = (nextPlayerIndex + playerMoves.size()) % playerMoves.size();
    }

    public static class Builder{
        private List<Player> players;
        private List<GameWinningStrategy> winningStrategies;
        private Integer dimension;
        private Builder(){
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<GameWinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(Integer dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() throws Exception {
            // validation
            // create and return new object for game
            /**
             * 1. Atleast 1 player
             * 2. All player cannot be bots
             */
            validations();
            return new Game(dimension,players,winningStrategies);
        }

        private void validations() throws Exception {
            if(players.size() < 1){
                throw new Exception();
            }

            validateNumberOfBots();
        }

        private void validateNumberOfBots() throws Exception {
            int botcount = 0;
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).getPlayerType().equals(PlayerType.BOT)){
                    botcount++;
                }
            }
            if(botcount == players.size()){
                throw new Exception();
            }
        }
    }
}
