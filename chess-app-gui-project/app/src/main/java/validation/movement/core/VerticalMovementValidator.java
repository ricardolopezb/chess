package validation.movement.core;

import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class VerticalMovementValidator extends AbstractMovementValidator {
    public VerticalMovementValidator() {
        super(new HashSet<>());
    }

    public VerticalMovementValidator(Set<MovementValidator> allowedMoves) {
        super(allowedMoves);
    }

    @Override
    public boolean validate(Turn turn) {
        Tile to = turn.getTo();
        Tile from = turn.getFrom();
        int toLetter = to.getLetter() - 96;
        int fromLetter = from.getLetter() - 96;
        return super.validateRestrictions(turn) && fromLetter == toLetter;
    }
}
