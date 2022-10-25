package validation.victory;

import chess.*;
import chess.board.Board;
import utils.exceptions.InvalidMoveException;
import validation.Validator;

import java.util.List;

public class CheckMateChecker implements VictoryChecker {

    // if king is in check, check for every piece of my color if there is a possible tile to move to

    private final Validator validator;

    public CheckMateChecker(Validator validator) {
        this.validator = validator;
    }

    // TODO test method
    @Override
    public boolean checkWin(Board board, boolean whiteTurn) throws InvalidMoveException {
        List<Tile> tiles = board.getTiles();
        Color myColor = whiteTurn ? Color.WHITE : Color.BLACK;
        Player temp = new Player(); // does not matter for this method
        for (Tile tile : tiles) {
            if(tile.hasPiece() && tile.getPiece().getColor() == myColor){
                // check if there is a possible tile to move to
                for (Tile targetTile : tiles) {
                    Turn possibleMove = new Turn(targetTile, tile, temp);
                    try {
                        if(validator.validate(possibleMove, board, whiteTurn)) return false;
                    } catch (InvalidMoveException e) {
                        continue;
                    }
                }
            }
        }
        return true;



    }
}
