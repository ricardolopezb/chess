package validation.movement.restrictions;

import chess.Piece;
import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class UniqueMovementValidator extends AbstractMovementValidator implements MovementValidator {

    public UniqueMovementValidator() {
        super(new HashSet<>());
    }


    @Override
    public boolean validate(Turn turn) {
        Tile from = turn.getFrom();
        Piece piece = from.getPiece();
        return !from.hasChanged() && from.toString().equals(piece.getId());
    }
}
