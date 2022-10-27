package chess;

public class Promoter {
    private final PieceName promoteFrom;
    private final PieceName promoteTo;

    public Promoter(PieceName promoteFrom, PieceName promoteTo){
        this.promoteFrom = promoteFrom;
        this.promoteTo = promoteTo;
    }

    public void checkPromotion(Turn turn){
        Tile to = turn.getTo();
        if(!to.hasPiece()) return;
        Piece movedPiece = to.getPiece();
        Color movedPieceColor = movedPiece.getColor();
        if(movedPiece.getName() != promoteFrom) return;

        if(movedPieceColor == Color.WHITE){
            if(to.getNumber() == 8){
                movedPiece.setName(promoteTo);
            }
        } else {
            if(to.getNumber() == 1){
                movedPiece.setName(promoteTo);
            }
        }

    }
}
