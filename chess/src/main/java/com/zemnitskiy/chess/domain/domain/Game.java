package com.zemnitskiy.chess.domain.domain;


import com.zemnitskiy.chess.domain.domain.exceptions.NotYourTurnException;
import com.zemnitskiy.chess.domain.domain.exceptions.WrongTurnException;
import com.zemnitskiy.chess.domain.domain.figures.Color;
import com.zemnitskiy.chess.domain.domain.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;

public class Game extends Observable {
    Logger log = LoggerFactory.getLogger(Game.class);
    private Board board;
    public boolean isWhiteNow = true;

    public Game(Board board) {
        this.board = board;
    }

    public void makeTurn(Position position1, Position position2){
      /*  if (board.getFigures()[position1.getVertical()][position1.getIntHorizontal()] == null){
            log.debug("Turn is impossible.Position 1 don't have a figure");
            throw new WrongTurnException("Turn is impossible.Position 1 don't have a figure");
        }

        //Position 1 and position 2 are the same color
        if(board.getFigures()[position2.getVertical()][position2.getIntHorizontal()] != null){
            if (board.getFigures()[position1.getVertical()][position1.getIntHorizontal()].getColor() ==
                    board.getFigures()[position2.getVertical()][position2.getIntHorizontal()].getColor()){
                log.debug("Turn is impossible. Position 1 and position 2 have the same color figures");
                throw new WrongTurnException("Turn is impossible. Position 1 and position 2 have the same color figures");
            }
        }
        if(!(board.getFigures()[position1.getVertical()][position1.getIntHorizontal()].getColor() == Color.BLACK ^ isWhiteNow)){
            log.info("Now is not your turn");
            throw new NotYourTurnException("Now is not your turn");

        }
        board.getFigures()[position1.getVertical()][position1.getIntHorizontal()]
                .isPossible(position1,position2, board.getFigures());



        log.debug("Turn is Possible");
        isWhiteNow = !isWhiteNow;
        Figure figureBuff = board.getFigures()[position1.getVertical()][position1.getIntHorizontal()];
        board.getFigures()[position1.getVertical()][position1.getIntHorizontal()] = null;
        board.getFigures()[position2.getVertical()][position2.getIntHorizontal()] = figureBuff;
        setChanged();
        log.debug("notifyObservers {}", countObservers());
        notifyObservers(this.board.toString());
*/
    }
    public void makeTurn(Turn turn){
        checkTurn(turn);
        board.makeTurn(turn);
        isWhiteNow = !isWhiteNow;
    }

    private void checkTurn(Turn turn){
        Figure startFigure = board.getFigure(turn.from);
        Figure distFigure = board.getFigure(turn.to);

        if (startFigure == null){
            log.debug("Turn is impossible.Position 1 don't have a figure");
            throw new WrongTurnException("Turn is impossible.Position 1 don't have a figure");
        }

        if( turn.from == turn.to){
            log.debug("Turn is impossible. Position 1 and position 2 are the same positions");
            throw new WrongTurnException("Turn is impossible. Position 1 and position 2 are the same positions");
        }

        if(startFigure.getColor() == Color.WHITE ^ isWhiteNow){
            log.info("Now is not your turn");
            throw new NotYourTurnException("Now is not your turn");
        }

        if( !(distFigure == null || startFigure.getColor() != distFigure.getColor())){
            log.debug("Turn is impossible. Position 1 and position 2 have the same color figures");
            throw new WrongTurnException("Turn is impossible. Position 1 and position 2 have the same color figures");
        }
    }
}
