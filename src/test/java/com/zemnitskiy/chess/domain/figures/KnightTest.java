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

class KnightTest {
    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    void isPossible() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e4"), new Knight(Color.WHITE));

        Set<Position> validPositions = new HashSet<>();
        validPositions.add(Position.fromString("d6"));
        validPositions.add(Position.fromString("f6"));

        validPositions.add(Position.fromString("g5"));
        validPositions.add(Position.fromString("g3"));

        validPositions.add(Position.fromString("f2"));
        validPositions.add(Position.fromString("d2"));

        validPositions.add(Position.fromString("c3"));
        validPositions.add(Position.fromString("c5"));

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
                log.debug(i + " "+ n + "Checked");
            }
        }
    }
}