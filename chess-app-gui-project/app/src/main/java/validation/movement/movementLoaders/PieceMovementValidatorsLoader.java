package validation.movement.movementLoaders;

import chess.PieceName;
import validation.movement.MovementValidator;

import java.util.Map;

public interface PieceMovementValidatorsLoader {

    Map<PieceName, MovementValidator> loadMovementValidators();
}
