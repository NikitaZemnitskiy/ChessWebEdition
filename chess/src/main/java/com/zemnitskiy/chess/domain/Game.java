package com.zemnitskiy.chess.domain;


import com.zemnitskiy.chess.domain.figures.Figure;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Game {
    private Board board;
    private boolean isWhiteNow = true;

    public Game(Board board) {
        this.board = board;
    }

    public boolean makeTurn(Position position1, Position position2){
        if (board.getFigures()[position1.getVertical()][position1.getIntHorizontal()] == null){
            log.debug("Turn is impossible.Position 1 don't have a figure");
            return false;
        }

        //Position 1 and position 2 are the same color
        if(board.getFigures()[position2.getVertical()][position2.getIntHorizontal()] != null){
            if (board.getFigures()[position1.getVertical()][position1.getIntHorizontal()].getColor() ==
                    board.getFigures()[position2.getVertical()][position2.getIntHorizontal()].getColor()){
                log.debug("Turn is impossible. Position 1 and position 2 have the same color figures");
                return false;
            }
        }
        if(! (board.getFigures()[position1.getVertical()][position1.getIntHorizontal()].getColor() == Color.BLACK ^ isWhiteNow)){
            log.info("Now is not your turn");
            return false;
        }
        if (!board.getFigures()[position1.getVertical()][position1.getIntHorizontal()]
                .isPossible(position1,position2, board.getFigures())){
            log.debug("Turn is impossible.");
            return false;
        }

        log.debug("Turn is Possible");
        isWhiteNow = !isWhiteNow;
        Figure figureBuff = board.getFigures()[position1.getVertical()][position1.getIntHorizontal()];
        board.getFigures()[position1.getVertical()][position1.getIntHorizontal()] = null;
        board.getFigures()[position2.getVertical()][position2.getIntHorizontal()] = figureBuff;
        return true;
    }
}
