package validation.movement;

import chess.Turn;

public interface MovementValidator {
    boolean validate(Turn turn);
}
