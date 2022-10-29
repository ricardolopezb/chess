package validation;

import chess.*;
import chess.board.Board;

public class PositionTileValidator {
    // que no este fuera del board
    // que no haya otra pieza mia
    // que no sea la misma tile
    // si hay una pieza en el start y es mia

    public boolean validatePositions(Turn turn, Board board, boolean whiteTurn){
        Color turnColor = whiteTurn ? Color.WHITE : Color.BLACK;
        Tile from = turn.getFrom();
        Tile to = turn.getTo();

        if(!board.tileExists(from) && !board.tileExists(to)) return false;
        if(from.equals(to)) return false;
        if(!from.hasPiece()) return false;
        if(from.getPiece().getColor() != turnColor) return false;
        if(to.hasPiece()) return to.getPiece().getColor() != turnColor;

        return true;
    }

}
