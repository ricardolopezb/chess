package validation.movement.core;

import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class DiagonalMovementValidator extends AbstractMovementValidator {


    public DiagonalMovementValidator(Set<MovementValidator> allowedMoves) {
        super(allowedMoves);
    }

    public DiagonalMovementValidator() {
        super(new HashSet<>());
    }

    // TODO to test
    @Override
    public boolean validate(Turn turn) {
        Tile to = turn.getTo();
        Tile from = turn.getFrom();

        int toLetter = to.getLetter() - 96;
        int toNumber = to.getNumber();

        int fromLetter = from.getLetter() - 96;
        int fromNumber = from.getNumber();

        return super.validateRestrictions(turn) && Math.abs(toLetter-fromLetter) == Math.abs(toNumber-fromNumber);
    }
}
