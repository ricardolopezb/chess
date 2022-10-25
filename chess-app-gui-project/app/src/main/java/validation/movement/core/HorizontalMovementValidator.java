package validation.movement.core;

import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class HorizontalMovementValidator extends AbstractMovementValidator {
    public HorizontalMovementValidator() {
        super(new HashSet<>());
    }

    public HorizontalMovementValidator(Set<MovementValidator> allowedMoves) {
        super(allowedMoves);
    }

    @Override
    public boolean validate(Turn turn) {
        Tile to = turn.getTo();
        Tile from = turn.getFrom();
        int toNumber = to.getNumber();
        int fromNumber = from.getNumber();
        return super.validateRestrictions(turn) && fromNumber == toNumber;

    }
}
