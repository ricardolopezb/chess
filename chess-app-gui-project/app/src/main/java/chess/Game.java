package chess;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.turn.manager.TurnManager;
import chess.turn.manager.TurnManagerFactory;
import utils.Coordinates;
import validation.Validator;

import java.util.Stack;

public class Game {
    private final Board board;
    private boolean whiteTurn;
    private final Stack<Turn> turnHistory;
    private final Validator validator;
    private final GameMode gameMode;
    private final Promoter promoter;
    private final TurnManager turnManager;
    private final PieceMover pieceMover;

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
        this.pieceMover = PieceMover.getInstance();

    }

    public boolean isWhiteTurn() {
        return turnManager.getCurrentTurnColor() == Color.WHITE;
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
        validator.validateWin(board, whiteTurn);

    }

    private void movePiece(Turn turn){
        pieceMover.movePieces(turn, board);
    }
}
