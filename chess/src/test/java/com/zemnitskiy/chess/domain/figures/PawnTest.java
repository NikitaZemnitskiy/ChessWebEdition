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

class PawnTest {
    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    void isPossibleToWhitePawnFromE2() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e2"), new Pawn(Color.WHITE));

        Set<Position> validPositions = new HashSet<>();
        validPositions.add(Position.fromString("e3"));
        validPositions.add(Position.fromString("e4"));


        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (Position.fromString("e2").equals(Position.fromCoordinates(i, n))) {
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e2"), Position.fromCoordinates(i, n));
                log.debug(turn.toString());
                if (validPositions.contains(turn.to)) {
                    board.makeTurn(turn);
                } else {
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
    void isPossibleToWhitePawnFromE2WhenE3IsNotEmpty() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e2"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("e3"), new Queen(Color.BLACK));
        Set<Position> validPositions = new HashSet<>();

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (Position.fromString("e2").equals(Position.fromCoordinates(i, n))) {
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e2"), Position.fromCoordinates(i, n));
                log.debug(turn.toString());
                if (validPositions.contains(turn.to)) {
                    board.makeTurn(turn);
                } else {
                    Throwable thrown = assertThrows(WrongTurnException.class, () -> {
                        board.makeTurn(turn);
                    });
                    assertNotNull(thrown.getMessage());
                }
                log.debug(turn + " - Checked");
            }
        }
    }

    @Test
    void isPossibleToWhitePawnFromE3() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e3"), new Pawn(Color.WHITE));
        Set<Position> validPositions = new HashSet<>();
        validPositions.add(Position.fromString("e4"));

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (Position.fromString("e3").equals(Position.fromCoordinates(i, n))) {
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e3"), Position.fromCoordinates(i, n));
                log.debug(turn.toString());
                if (validPositions.contains(turn.to)) {
                    board.makeTurn(turn);
                } else {
                    Throwable thrown = assertThrows(WrongTurnException.class, () -> {
                        board.makeTurn(turn);
                    });
                    assertNotNull(thrown.getMessage());
                }
                log.debug(turn + " - Checked");
            }
        }
    }

    @Test
    void isPossibleToWhitePawnFromE3WhenD4AndF4AreNotEmpty() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e3"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("d4"), new Queen(Color.BLACK));
        board.addFigureToBoard(Position.fromString("f4"), new Pawn(Color.BLACK));
        Set<Position> validPositions = new HashSet<>();
        validPositions.add(Position.fromString("e4"));
        validPositions.add(Position.fromString("d4"));
        validPositions.add(Position.fromString("f4"));

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (Position.fromString("e3").equals(Position.fromCoordinates(i, n))) {
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e3"), Position.fromCoordinates(i, n));
                log.debug(turn.toString());
                if (validPositions.contains(turn.to)) {
                    board.makeTurn(turn);
                } else {
                    Throwable thrown = assertThrows(WrongTurnException.class, () -> {
                        board.makeTurn(turn);
                    });
                    assertNotNull(thrown.getMessage());
                }
                log.debug(turn + " - Checked");
            }
        }
    }

    @Test
    void isPossibleToBlackPawnFromE7() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e7"), new Pawn(Color.BLACK));

        Set<Position> validPositions = new HashSet<>();
        validPositions.add(Position.fromString("e6"));
        validPositions.add(Position.fromString("e5"));


        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (Position.fromString("e7").equals(Position.fromCoordinates(i, n))) {
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e7"), Position.fromCoordinates(i, n));
                log.debug(turn.toString());
                if (validPositions.contains(turn.to)) {
                    board.makeTurn(turn);
                } else {
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
    void isPossibleToBlackPawnFromE7WhenE6IsNotEmpty() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e7"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("e6"), new Queen(Color.WHITE));
        Set<Position> validPositions = new HashSet<>();

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (Position.fromString("e7").equals(Position.fromCoordinates(i, n))) {
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e7"), Position.fromCoordinates(i, n));
                log.debug(turn.toString());
                if (validPositions.contains(turn.to)) {
                    board.makeTurn(turn);
                } else {
                    Throwable thrown = assertThrows(WrongTurnException.class, () -> {
                        board.makeTurn(turn);
                    });
                    assertNotNull(thrown.getMessage());
                }
                log.debug(turn + " - Checked");
            }
        }
    }

    @Test
    void isPossibleToBlackPawnFromE6() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e6"), new Pawn(Color.BLACK));
        Set<Position> validPositions = new HashSet<>();
        validPositions.add(Position.fromString("e5"));

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (Position.fromString("e6").equals(Position.fromCoordinates(i, n))) {
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e6"), Position.fromCoordinates(i, n));
                log.debug(turn.toString());
                if (validPositions.contains(turn.to)) {
                    board.makeTurn(turn);
                } else {
                    Throwable thrown = assertThrows(WrongTurnException.class, () -> {
                        board.makeTurn(turn);
                    });
                    assertNotNull(thrown.getMessage());
                }
                log.debug(turn + " - Checked");
            }
        }
    }

    @Test
    void isPossibleToBlackPawnFromE6WhenD5AndF5AreNotEmpty() {

        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e6"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("d5"), new Queen(Color.WHITE));
        board.addFigureToBoard(Position.fromString("f5"), new Pawn(Color.WHITE));
        Set<Position> validPositions = new HashSet<>();
        validPositions.add(Position.fromString("e5"));
        validPositions.add(Position.fromString("d5"));
        validPositions.add(Position.fromString("f5"));

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (Position.fromString("e6").equals(Position.fromCoordinates(i, n))) {
                    continue;
                }
                Turn turn = new Turn(Position.fromString("e6"), Position.fromCoordinates(i, n));
                log.debug(turn.toString());
                if (validPositions.contains(turn.to)) {
                    board.makeTurn(turn);
                } else {
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