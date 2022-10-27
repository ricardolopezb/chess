package validation;

import chess.*;
import validation.movement.MovementValidator;
import validation.movement.PieceMovementValidator;
import validation.movement.core.*;
import validation.movement.special.CaptureMovementValidator;
import validation.movement.special.MoveCountValidator;
import validation.movement.special.PieceSwitchValidator;
import validation.movement.special.UniqueMovementValidator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MoveValidator {
    private Map<PieceName, MovementValidator> pieceMovements;

    public MoveValidator(GameMode gameMode){
        this.pieceMovements = new HashMap<>();
        switch(gameMode){
            // cargar el map con los moves correspondientes
            case CLASSIC -> loadMapWithClassic();

        }
    }

    public boolean validate(Turn turn){
        Tile from = turn.getFrom();
        MovementValidator pieceMoveValidator = pieceMovements.get(from.getPiece().getName());
        return pieceMoveValidator.validate(turn);
    }

    // TODO ojo con el peon con el movimiento para comer y que no coma para adelante
    public void loadMapWithClassic(){
        // Pawn movement
        Set<MovementValidator> pawnMoves = new HashSet<>();

        Set<MovementValidator> verticalLimit = new HashSet<>();
        verticalLimit.add(new LimitMovementValidator(1));
        verticalLimit.add(new UnidirectionalMovementValidator());

        Set<MovementValidator> verticalLimit2 = new HashSet<>();
        verticalLimit2.add(new LimitMovementValidator(2));
        verticalLimit2.add( new UniqueMovementValidator());
        verticalLimit2.add(new UnidirectionalMovementValidator());

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
        rookMoves.add(new PieceSwitchValidator(rookRestrictions, PieceName.ROOK, PieceName.KING));
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
        //kingSwitchRestrictions.add(new MoveCountValidator());
        kingMoves.add(new PieceSwitchValidator(kingSwitchRestrictions, PieceName.ROOK, PieceName.KING));
        kingMoves.add(new VerticalMovementValidator(kingLimitRestriction));
        kingMoves.add(new HorizontalMovementValidator(kingLimitRestriction));
        kingMoves.add(new DiagonalMovementValidator(kingLimitRestriction));
        MovementValidator kingMovement = new PieceMovementValidator(kingMoves);
        pieceMovements.put(PieceName.KING, kingMovement);

    }

}
