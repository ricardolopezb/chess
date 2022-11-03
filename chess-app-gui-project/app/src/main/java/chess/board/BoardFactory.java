package chess.board;

import chess.*;

public class BoardFactory {
    private BoardCreator boardCreator;

    public BoardFactory(GameMode mode) {
        switch (mode){
            case CLASSIC -> {
                boardCreator = new ClassicBoardCreator();
            }
            case CAPABLANCA -> {
                boardCreator = new CapablancaBoardCreator();
            }
            case BERLIN -> {
                boardCreator = new ClassicBoardCreator();
            }
        }
    }

    public Board createBoard(){
        return boardCreator.create();
    }


}
