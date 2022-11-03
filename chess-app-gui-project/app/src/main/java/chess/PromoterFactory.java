package chess;

public class PromoterFactory {

    public Promoter createPromoter(GameMode gameMode){
        return switch (gameMode){
            case CLASSIC, CAPABLANCA -> new Promoter(PieceName.PAWN, PieceName.QUEEN);
            default -> null;
        };


    }
}
