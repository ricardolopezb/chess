package validation.movement.movementLoaders;

import chess.PieceName;
import validation.movement.MovementValidator;
import validation.movement.PieceMovementValidator;
import validation.movement.core.*;
import validation.movement.special.CaptureMovementValidator;
import validation.movement.special.CaptureNotAllowedValidator;
import validation.movement.special.CastlingValidator;
import validation.movement.special.UniqueMovementValidator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClassicPieceMovementValidatorsLoader implements PieceMovementValidatorsLoader {
    @Override
    public Map<PieceName, MovementValidator> loadMovementValidators() {
        Map<PieceName, MovementValidator> pieceMovements = new HashMap<>();

        Set<MovementValidator> pawnMoves = new HashSet<>();

        Set<MovementValidator> verticalLimit = new HashSet<>();
        verticalLimit.add(new LimitMovementValidator(1));
        verticalLimit.add(new UnidirectionalMovementValidator());
        verticalLimit.add(new CaptureNotAllowedValidator());

        Set<MovementValidator> verticalLimit2 = new HashSet<>();
        verticalLimit2.add(new LimitMovementValidator(2));
        verticalLimit2.add( new UniqueMovementValidator());
        verticalLimit2.add(new UnidirectionalMovementValidator());
        verticalLimit2.add(new CaptureNotAllowedValidator());

        pawnMoves.add(new VerticalMovementValidator(verticalLimit));
        pawnMoves.add(new VerticalMovementValidator(verticalLimit2));
        pawnMoves.add(new CaptureMovementValidator());
        MovementValidator pawnMovement = new PieceMovementValidator(pawnMoves);
        pieceMovements.put(PieceName.PAWN, pawnMovement);

        // Rook movement
        Set<MovementValidator> rookMoves = new HashSet<>();
        Set<MovementValidator> rookRestrictions = new HashSet<>();
        rookRestrictions.add(new UniqueMovementValidator());
        rookMoves.add(new VerticalMovementValidator());
        rookMoves.add(new HorizontalMovementValidator());
        MovementValidator rookMovement = new PieceMovementValidator(rookMoves);
        pieceMovements.put(PieceName.ROOK, rookMovement);

        // Bishop movement
        Set<MovementValidator> bishopMoves = new HashSet<>();
        bishopMoves.add(new DiagonalMovementValidator());
        MovementValidator bishopMovement = new PieceMovementValidator(bishopMoves);
        pieceMovements.put(PieceName.BISHOP, bishopMovement);


        // Knight movement
        Set<MovementValidator> knightMoves = new HashSet<>();
        knightMoves.add(new VariableMovementValidator(1,2,1));
        knightMoves.add(new VariableMovementValidator(2,1,1));
        MovementValidator knightMovement = new PieceMovementValidator(knightMoves);
        pieceMovements.put(PieceName.KNIGHT, knightMovement);

        // Queen movement
        Set<MovementValidator> queenMoves = new HashSet<>();
        queenMoves.add(new VerticalMovementValidator());
        queenMoves.add(new HorizontalMovementValidator());
        queenMoves.add(new DiagonalMovementValidator());
        MovementValidator queenMovement = new PieceMovementValidator(queenMoves);
        pieceMovements.put(PieceName.QUEEN, queenMovement);


        //King movement
        Set<MovementValidator> kingMoves = new HashSet<>();
        Set<MovementValidator> kingLimitRestriction = new HashSet<>();
        kingLimitRestriction.add(new LimitMovementValidator(1));
        Set<MovementValidator> kingSwitchRestrictions = new HashSet<>();
        kingSwitchRestrictions.add(new UniqueMovementValidator());
        kingMoves.add(new CastlingValidator(kingSwitchRestrictions, PieceName.KING, PieceName.ROOK));
        kingMoves.add(new VerticalMovementValidator(kingLimitRestriction));
        kingMoves.add(new HorizontalMovementValidator(kingLimitRestriction));
        kingMoves.add(new DiagonalMovementValidator(kingLimitRestriction));
        MovementValidator kingMovement = new PieceMovementValidator(kingMoves);
        pieceMovements.put(PieceName.KING, kingMovement);

        return pieceMovements;
    }
}
