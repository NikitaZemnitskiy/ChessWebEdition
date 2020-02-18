package com.zemnitskiy.chess.domain;


import com.zemnitskiy.chess.domain.exceptions.NotYourTurnException;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;
import com.zemnitskiy.chess.domain.figures.Figure;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Observable;
import java.util.Observer;

@Slf4j
@Data
public class Game extends Observable{
    private Board board;
    private boolean isWhiteNow = true;

    public Game(Board board) {
        this.board = board;
    }

    public void makeTurn(Position position1, Position position2){
        if (board.getFigures()[position1.getVertical()][position1.getIntHorizontal()] == null){
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

    }

}
