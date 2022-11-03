package validation.movement.restrictions;

import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class LimitMovementValidator extends AbstractMovementValidator {
    private final int limit;
    public LimitMovementValidator(int limit) {
        super(new HashSet<>());
        this.limit = limit;
    }

    @Override
    public boolean validate(Turn turn) {
        Tile to = turn.getTo();
        Tile from = turn.getFrom();

        int toLetter = to.getLetter() - 96;
        int toNumber = to.getNumber();

        int fromLetter = from.getLetter() - 96;
        int fromNumber = from.getNumber();

        return super.validateRestrictions(turn) && Math.abs(toLetter-fromLetter) <= limit && Math.abs(toNumber-fromNumber) <= limit;

    }
}
