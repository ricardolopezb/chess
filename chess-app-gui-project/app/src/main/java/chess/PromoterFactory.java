package chess;

public class PromoterFactory {

    public Promoter createPromoter(GameMode gameMode){
        // switch con gamemodes

        if(gameMode == GameMode.CLASSIC){
            return new Promoter(PieceName.PAWN, PieceName.QUEEN);
        }
        return null;

    }
}
