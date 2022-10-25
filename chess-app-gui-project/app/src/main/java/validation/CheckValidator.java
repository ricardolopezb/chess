package validation;

import chess.*;
import chess.board.Board;
import utils.exceptions.InvalidMoveException;

import java.util.List;

public class CheckValidator {
    private final MoveValidator moveValidator;
    private final PathValidator pathValidator;

    public CheckValidator(MoveValidator moveValidator, PathValidator pathValidator) {
        this.moveValidator = moveValidator;
        this.pathValidator = pathValidator;
    }

    public boolean validate(Turn turn, Board board, boolean whiteTurn) throws InvalidMoveException {
        Tile from = turn.getFrom();
        Tile to = turn.getTo();
        Piece pieceInTo = to.getPiece();
        board.movePiece(from, to);
        // how to optimize this?
        Color enemyColor = whiteTurn ? Color.BLACK : Color.WHITE;
        Color myColor = whiteTurn ? Color.WHITE : Color.BLACK;
        Tile myKingTile = board.getKingTile(myColor);
        List<Tile> tiles = board.getTiles();
        for (Tile tile : tiles) {
            if(tile.hasPiece() && tile.getPiece().getColor() == enemyColor){
                Turn possibleTurn = new Turn(myKingTile, tile, turn.getPlayer());
                if(moveValidator.validate(possibleTurn) && pathValidator.validate(possibleTurn, board)) {
                    board.movePiece(to, from);
                    to.setPiece(pieceInTo);
                    throw new InvalidMoveException("Your king is in check");
                };
            }
        }

        board.movePiece(to, from);
        to.setPiece(pieceInTo);

        // returns true if there is no check
        return true;
    }

}
