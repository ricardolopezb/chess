package validation;

import chess.*;
import validation.movement.MovementValidator;
import validation.movement.PieceMovementValidator;
import validation.movement.core.*;
import validation.movement.movementLoaders.CapablancaPieceMovementValidatorsLoader;
import validation.movement.movementLoaders.ClassicPieceMovementValidatorsLoader;
import validation.movement.movementLoaders.PieceMovementValidatorsLoader;
import validation.movement.special.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MoveValidator {
    private Map<PieceName, MovementValidator> pieceMovements;


    public MoveValidator(GameMode gameMode){
        PieceMovementValidatorsLoader loader;
        switch(gameMode){
            // cargar el map con los moves correspondientes
            case CLASSIC -> loader = new ClassicPieceMovementValidatorsLoader();
            case CAPABLANCA -> loader = new CapablancaPieceMovementValidatorsLoader();
            default -> loader = new ClassicPieceMovementValidatorsLoader(); // agregar gamemodes con sus piece movements...

        }
        this.pieceMovements = loader.loadMovementValidators();
    }

    public boolean validate(Turn turn){
        Tile from = turn.getFrom();
        MovementValidator pieceMoveValidator = pieceMovements.get(from.getPiece().getName());
        return pieceMoveValidator.validate(turn);
    }
}
