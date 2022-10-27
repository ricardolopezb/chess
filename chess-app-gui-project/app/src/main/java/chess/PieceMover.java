package chess;

import chess.board.Board;
import utils.exceptions.TileNotFoundException;

public class PieceMover {

    private boolean switchTurn;
    private static PieceMover instance = new PieceMover();

    private PieceMover(){
        switchTurn = false;
    }

    public static PieceMover getInstance(){
        return PieceMover.instance;
    }

    public void movePieces(Turn turn, Board board){
        Tile from = turn.getFrom();
        Tile to = turn.getTo();
        Color turnColor = from.getPiece().getColor();
        if(switchTurn) {
            try {
                    if (turnColor == Color.WHITE) {
                        Tile kingDest = board.getTile('g', 1);
                        Tile rookDest = board.getTile('f', 1);
                        if (from.getPiece().getName() == PieceName.KING) {
                            board.movePiece(from, kingDest);
                            board.movePiece(to, rookDest);
                        } else {
                            board.movePiece(to, kingDest);
                            board.movePiece(from, rookDest);
                        }

                    } else {
                        Tile kingDest = board.getTile('g', 8);
                        Tile rookDest = board.getTile('f', 8);
                        if (from.getPiece().getName() == PieceName.KING) {
                            board.movePiece(from, kingDest);
                            board.movePiece(to, rookDest);
                        } else {
                            board.movePiece(to, kingDest);
                            board.movePiece(from, rookDest);
                        }
                    }
                }

            catch (TileNotFoundException e) {
                System.out.println("not found");
            }

        } else {
            board.movePiece(from, to);
        }

        setSwitchTurn(false);
        from.setHasChanged(true);
        to.setHasChanged(true);
    }

    public void setSwitchTurn(boolean switchTurn) {
        this.switchTurn = switchTurn;
    }
}
