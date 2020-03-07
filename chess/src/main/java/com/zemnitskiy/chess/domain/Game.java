package com.zemnitskiy.chess.domain;


import com.zemnitskiy.chess.domain.exceptions.NotYourTurnException;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;
import com.zemnitskiy.chess.domain.figures.Color;
import com.zemnitskiy.chess.domain.figures.Figure;
import com.zemnitskiy.chess.entity.User;
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


    public void makeTurn(Turn turn){
        checkTurn(turn);
        board.makeTurn(turn);
        board.figures[turn.to.x][turn.to.y] = board.figures[turn.from.x][turn.from.y];
        board.figures[turn.from.x][turn.from.y] = null;
        log.debug(turn + " made");
        isWhiteNow = !isWhiteNow;
        setChanged();
        log.debug("notifyObservers {}", countObservers());
        notifyObservers(this.board.toString());
    }

    private void checkTurn(Turn turn){
        Figure startFigure = board.getFigure(turn.from);
        Figure distFigure = board.getFigure(turn.to);

        if (startFigure == null){
            log.debug("Turn is impossible.{} don't have a figure", turn.from);
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

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                ", isWhiteNow=" + isWhiteNow +
                '}';
    }
}
