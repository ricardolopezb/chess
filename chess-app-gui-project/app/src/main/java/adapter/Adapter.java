package adapter;

import chess.*;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.Position;
import org.jetbrains.annotations.NotNull;
import utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Adapter {

    public Position convertTileToPosition(Tile tile) {
        int castedLetter = tile.getLetter() - 96;
        return new Position(tile.getNumber(), castedLetter);
    }

    public Move convertTurnToMove(Turn turn){
        Tile from = turn.getFrom();
        Tile to = turn.getTo();
        Position fromPosition = convertTileToPosition(from);
        Position toPosition = convertTileToPosition(to);
        return new Move(fromPosition, toPosition);
    }

    public List<ChessPiece> convertPieceToChessPiece(List<Tile> boardTiles){
        List<ChessPiece> toReturn = new ArrayList<>();
        for (int i = 0; i < boardTiles.size(); i++) {
            if(boardTiles.get(i).hasPiece()){
                Piece piece = boardTiles.get(i).getPiece();
                Position position = convertTileToPosition(boardTiles.get(i));
                PlayerColor playerColor = adaptColorToPlayerColor(piece.getColor());
                ChessPiece chessPiece = new ChessPiece(piece.getId(), playerColor,  position, getStringFromPieceName(piece.getName()));
                toReturn.add(chessPiece);
            }

        }
        return toReturn;
    }


    private String getStringFromPieceName(PieceName pieceName){
        return switch (pieceName) {
            case PAWN -> "pawn";
            case ROOK -> "rook";
            case KNIGHT -> "knight";
            case BISHOP -> "bishop";
            case QUEEN -> "queen";
            case KING -> "king";
            case ARCHBISHOP -> "archbishop";
            case CHANCELLOR -> "chancellor";
            default -> "-";
        };
    }
    private PlayerColor adaptColorToPlayerColor(Color color) {
        if(color == Color.WHITE) return PlayerColor.WHITE;
        else return PlayerColor.BLACK;
    }

    @NotNull
    public List<Coordinates> getCoordinatesFromMove(@NotNull Move move) {
        Position moveFrom = move.getFrom();
        Position moveTo = move.getTo();

        Coordinates cFrom = new Coordinates((char) (moveFrom.getColumn()+96), moveFrom.getRow());
        Coordinates cTo = new Coordinates((char) (moveTo.getColumn()+96), moveTo.getRow());
        List<Coordinates> coords = new ArrayList<>(2);
        coords.add(cFrom);
        coords.add(cTo);
        return coords;
    }
}
