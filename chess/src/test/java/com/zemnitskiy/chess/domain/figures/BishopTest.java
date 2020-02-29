package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {
    Logger log = LoggerFactory.getLogger(getClass());
    @Test
    void isPossible() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e4"), new Bishop(Color.WHITE));
        Set<Position> validPositions = new HashSet<>();
        //diagonal a8 - h1
        validPositions.add(Position.fromString("a8"));
        validPositions.add(Position.fromString("b7"));
        validPositions.add(Position.fromString("c6"));
        validPositions.add(Position.fromString("d5"));
        validPositions.add(Position.fromString("f3"));
        validPositions.add(Position.fromString("g2"));
        validPositions.add(Position.fromString("h1"));
        //diagonal b1 - h7
        validPositions.add(Position.fromString("b1"));
        validPositions.add(Position.fromString("c2"));
        validPositions.add(Position.fromString("d3"));
        validPositions.add(Position.fromString("f5"));
        validPositions.add(Position.fromString("g6"));
        validPositions.add(Position.fromString("h7"));

        for(int i = 0; i<8 ;i++){
            for(int n = 0; n<8 ;n++){
                if(Position.fromString("e4").equals(Position.fromCoordinates(i,n))){
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e4"), Position.fromCoordinates(i,n));
                log.debug(turn.toString());

                if(validPositions.contains(turn.to)){
                    board.makeTurn(turn);
                }

                else {
                    Throwable thrown = assertThrows(WrongTurnException.class, () -> {
                        board.makeTurn(turn);
                    });
                    assertTrue(thrown.getMessage().contains(turn.toString()));
                }
                log.debug(turn + " - Checked");
            }
        }
    }

}