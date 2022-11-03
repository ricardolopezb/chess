package chess.board;

import chess.Color;
import chess.Piece;
import chess.PieceName;
import chess.Tile;

import java.util.Arrays;
import java.util.List;

public class CapablancaBoardCreator implements BoardCreator{
    @Override
    public Board create() {
        List<Tile> generatedTiles = generateTiles(10,10);
        insertCapablancaPieces(generatedTiles);
        return new Board(generatedTiles);
    }

    private void insertCapablancaPieces(List<Tile> generatedTiles) {
        Piece[] blackPiecesArray = {
                new Piece(PieceName.ROOK, Color.BLACK),
                new Piece(PieceName.KNIGHT, Color.BLACK),
                new Piece(PieceName.CHANCELLOR, Color.BLACK),
                new Piece(PieceName.BISHOP, Color.BLACK),
                new Piece(PieceName.KING, Color.BLACK),
                new Piece(PieceName.QUEEN, Color.BLACK),
                new Piece(PieceName.BISHOP, Color.BLACK),
                new Piece(PieceName.ARCHBISHOP, Color.BLACK),
                new Piece(PieceName.KNIGHT, Color.BLACK),
                new Piece(PieceName.ROOK, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK),
                new Piece(PieceName.PAWN, Color.BLACK)
        };

        List<Piece> blacks = Arrays.asList(blackPiecesArray);


        Piece[] whitePiecesArray = {
                new Piece(PieceName.ROOK, Color.WHITE),
                new Piece(PieceName.KNIGHT, Color.WHITE),
                new Piece(PieceName.ARCHBISHOP, Color.WHITE),
                new Piece(PieceName.BISHOP, Color.WHITE),
                new Piece(PieceName.QUEEN, Color.WHITE),
                new Piece(PieceName.KING, Color.WHITE),
                new Piece(PieceName.BISHOP, Color.WHITE),
                new Piece(PieceName.CHANCELLOR, Color.WHITE),
                new Piece(PieceName.KNIGHT, Color.WHITE),
                new Piece(PieceName.ROOK, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE),
                new Piece(PieceName.PAWN, Color.WHITE)
        };
        List<Piece> whites = Arrays.asList(whitePiecesArray);

        for (int i = 0; i < whites.size(); i++) {
            generatedTiles.get(i).setPiece(whites.get(i));
            whites.get(i).setId(generatedTiles.get(i).toString());

        }
        int j=0;
        for (int i = generatedTiles.size()-1; i >= generatedTiles.size()-blacks.size() ; i--, j++) {
            generatedTiles.get(i).setPiece(blacks.get(j));
            blacks.get(j).setId(generatedTiles.get(i).toString());

        }
    }
}
