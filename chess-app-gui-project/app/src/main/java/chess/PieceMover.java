package chess;

import chess.board.Board;
import utils.CoordinatePair;
import utils.exceptions.TileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PieceMover {

    private boolean switchTurn;
    private List<CoordinatePair> castlingMoves;
    private static final PieceMover instance = new PieceMover();

    private PieceMover(){
        switchTurn = false;
        this.castlingMoves = new ArrayList<>();
    }

    public static PieceMover getInstance(){
        return PieceMover.instance;
    }

    public void movePieces(Turn turn, Board board){
        Tile from = turn.getFrom();
        Tile to = turn.getTo();
        Color turnColor = from.getPiece().getColor();

        if(!castlingMoves.isEmpty()) {
            CoordinatePair rookMove = castlingMoves.get(0);
            CoordinatePair kingMove = castlingMoves.get(1);

            try {
                Tile rookFrom = board.getTile(rookMove.from());
                Tile rookTo = board.getTile(rookMove.to());
                Tile kingFrom = board.getTile(kingMove.from());
                Tile kingTo = board.getTile(kingMove.to());

                board.movePiece(rookFrom, rookTo);
                board.movePiece(kingFrom, kingTo);
            } catch (TileNotFoundException e) {
                throw new RuntimeException(e);
            }finally {
                this.castlingMoves = new ArrayList<>();
            }

        } else{
            board.movePiece(from, to);

        }


        from.setHasChanged(true);
        to.setHasChanged(true);
    }

    public void setSwitchTurn(boolean switchTurn) {
        this.switchTurn = switchTurn;
    }

    public void moveCastling(List<CoordinatePair> moves) {
        this.castlingMoves = moves;
    }

    public void clearCastling() {
        this.castlingMoves = new ArrayList<>();
    }
}
