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

import static org.junit.jupiter.api.Assertions.*;

class CastleTest {

    Logger log = LoggerFactory.getLogger(getClass());
    @Test
    void isPossible() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e4"), new Castle(Color.WHITE));

        Set<Position> validPositions = new HashSet<>();
        //horizontal a4 - h4
        validPositions.add(Position.fromString("a4"));
        validPositions.add(Position.fromString("b4"));
        validPositions.add(Position.fromString("c4"));
        validPositions.add(Position.fromString("d4"));
        validPositions.add(Position.fromString("f4"));
        validPositions.add(Position.fromString("g4"));
        validPositions.add(Position.fromString("h4"));

        //vertical e1 - e8
        validPositions.add(Position.fromString("e1"));
        validPositions.add(Position.fromString("e2"));
        validPositions.add(Position.fromString("e3"));
        validPositions.add(Position.fromString("e5"));
        validPositions.add(Position.fromString("e6"));
        validPositions.add(Position.fromString("e7"));
        validPositions.add(Position.fromString("e8"));

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

    @Test
    void PossibleMovesFromE4WithFigures() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e4"), new Castle(Color.WHITE));
        board.addFigureToBoard(Position.fromString("c4"), new Queen(Color.BLACK));
        board.addFigureToBoard(Position.fromString("g4"), new Queen(Color.BLACK));
        board.addFigureToBoard(Position.fromString("e6"), new Queen(Color.BLACK));
        board.addFigureToBoard(Position.fromString("e2"), new Queen(Color.BLACK));

        Set<Position> validPositions = new HashSet<>();

        //horizontal a4 - h4
        validPositions.add(Position.fromString("c4"));
        validPositions.add(Position.fromString("d4"));
        validPositions.add(Position.fromString("f4"));
        validPositions.add(Position.fromString("g4"));

        //vertical e1 - e8
        validPositions.add(Position.fromString("e2"));
        validPositions.add(Position.fromString("e3"));
        validPositions.add(Position.fromString("e5"));
        validPositions.add(Position.fromString("e6"));

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
                    assertNotNull(thrown.getMessage());
                }
                log.debug(turn + " - Checked");
            }
        }
    }

}