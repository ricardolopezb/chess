package chess;

public class CaptureTurn extends Turn{

    private final Piece capturedPiece;
    public CaptureTurn(Tile to, Tile from, Player player,Piece capturedPiece) {
        super(to, from, player);
        this.capturedPiece = capturedPiece;
    }
}
