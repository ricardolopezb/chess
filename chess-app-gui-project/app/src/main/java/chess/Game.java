package chess;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.turn.manager.TurnManager;
import chess.turn.manager.TurnManagerFactory;
import utils.Coordinates;
import validation.Validator;

import java.util.Stack;

public class Game {
    private Board board;
    private boolean whiteTurn;
    private Stack<Turn> turnHistory;
    private Validator validator;
    private GameMode gameMode;
    private Promoter promoter;
    private TurnManager turnManager;

    public Game(GameMode gameMode) {
        this.gameMode=gameMode;
        this.whiteTurn = true;
        this.turnHistory = new Stack<>();
        this.validator = new Validator(gameMode);
        BoardFactory bf = new BoardFactory(gameMode);
        this.board = bf.createBoard();
        PromoterFactory pf = new PromoterFactory();
        this.promoter = pf.createPromoter(gameMode);
        this.turnManager = TurnManagerFactory.forGameMode(gameMode);



    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public Board getBoard() {
        return this.board;
    }

    public void move(Coordinates from, Coordinates to, Player player) throws Exception{
        Tile fromTile = board.getTile(from);
        Tile toTile = board.getTile(to);
        Turn turn = new Turn(toTile, fromTile, player);
        if(!validator.validate(turn, board, whiteTurn)) throw new Exception("Invalid move!");
        movePiece(turn);
        turnHistory.push(turn);
        promoter.checkPromotion(turn);
        turnManager.handleMove();
        this.whiteTurn = turnManager.getCurrentTurnColor() == Color.WHITE;
        validator.validateWin(board, whiteTurn); // ojo con el cambio del whiteTurn ahi, creo que esta bien igual

    }

    private void movePiece(Turn turn){
        Tile from = turn.getFrom();
        Tile to = turn.getTo();
        board.movePiece(from, to);
        from.setHasChanged(true);
        to.setHasChanged(true);
    }
}
