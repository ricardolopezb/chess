package validation;

import chess.PieceMover;
import chess.board.Board;
import chess.GameMode;
import chess.Turn;
import utils.exceptions.InvalidMoveException;
import utils.exceptions.VictoryException;
import validation.victory.VictoryChecker;
import validation.victory.VictoryCheckerFactory;

import java.util.ArrayList;

public class Validator {
    private final PositionTileValidator positionTileValidator;
    private final MoveValidator moveValidator;
    private final PathValidator pathValidator;
    private final CheckValidator checkValidator;
    private final VictoryChecker victoryChecker;


    public Validator(GameMode gameMode) {
        this.positionTileValidator = new PositionTileValidator();
        this.moveValidator = new MoveValidator(gameMode);
        this.pathValidator = new PathValidator();
        this.checkValidator = new CheckValidator(moveValidator, pathValidator);
        VictoryCheckerFactory vcf = new VictoryCheckerFactory(this);
        this.victoryChecker = vcf.getVictoryCheckerForGameMode(gameMode);

    }

    public boolean validate(Turn turn, Board board, boolean turnColor) throws InvalidMoveException {
//        return positionTileValidator.validatePositions(turn, board, turnColor)
//                && moveValidator.validate(turn)
//                && pathValidator.validate(turn, board)
//                && checkValidator.validate(turn, board, turnColor);

        if(positionTileValidator.validatePositions(turn, board, turnColor)
                && moveValidator.validate(turn)
                && pathValidator.validate(turn, board)
                && checkValidator.validate(turn, board, turnColor)) return true;
        else {
            PieceMover.getInstance().clearCastling();
            throw new InvalidMoveException("Invalid move!");
        }
    }

    public void validateWin(Board board, boolean whiteTurn) throws InvalidMoveException, VictoryException {
        if(victoryChecker.checkWin(board, whiteTurn)) {
            System.out.println("You won!");
            throw new VictoryException("Chicken winner dinner dinner");
        }
    }
}
