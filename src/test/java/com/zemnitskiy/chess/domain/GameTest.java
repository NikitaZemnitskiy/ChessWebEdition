package com.zemnitskiy.chess.domain;

import com.zemnitskiy.chess.domain.exceptions.ImpossiblePositionException;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;
import com.zemnitskiy.chess.domain.figures.Color;
import com.zemnitskiy.chess.domain.figures.Figure;
import com.zemnitskiy.chess.domain.figures.Pawn;
import com.zemnitskiy.chess.domain.figures.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void makeWrongTurnFromE4ToE4() {
        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e4"), new Queen(Color.WHITE));
        Game game = new Game(board);
        Turn turn = new Turn(Position.fromString("e4"), Position.fromString("e4"));

        Throwable thrown = assertThrows(WrongTurnException.class, () -> {
            game.makeTurn(turn);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void tryToEatYourFigure() {
        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e4"), new Queen(Color.WHITE));
        board.addFigureToBoard(Position.fromString("e3"), new Pawn(Color.WHITE));
        Game game = new Game(board);
        Turn turn = new Turn(Position.fromString("e4"), Position.fromString("e3"));

        Throwable thrown = assertThrows(WrongTurnException.class, () -> {
            game.makeTurn(turn);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void tryToMakeTurnWithOutFigure() {
        Board board = new Board();
        Game game = new Game(board);
        Turn turn = new Turn(Position.fromString("e4"), Position.fromString("e3"));

        Throwable thrown = assertThrows(WrongTurnException.class, () -> {
            game.makeTurn(turn);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void tryToMakeTurn() {
        Board board = new Board();
        Figure queen = new Queen(Color.WHITE);
        board.addFigureToBoard(Position.fromString("e4"), queen);
        Game game = new Game(board);
        Turn turn = new Turn(Position.fromString("e4"), Position.fromString("e3"));
        game.makeTurn(turn);
        assertEquals(queen, board.getFigure(Position.fromString("e3")));
    }

    @Test
    void tryToTransformWhitePawn() {
        Board board = new Board();
        Figure pawn = new Pawn(Color.WHITE);
        board.addFigureToBoard(Position.fromString("e7"), pawn);
        Game game = new Game(board);
        Turn turn = new Turn(Position.fromString("e7"), Position.fromString("e8"));
        game.makeTurn(turn);
        assertEquals(board.getFigure(Position.fromString("e8")).getClass(), Queen.class);
    }

    @Test
    void tryToTransformBlackPawn() {
        Board board = new Board();
        Figure pawn = new Pawn(Color.BLACK);

        board.addFigureToBoard(Position.fromString("e2"), pawn);
        Game game = new Game(board);
        game.isWhiteNow = false;
        Turn turn = new Turn(Position.fromString("e2"), Position.fromString("e1"));
        game.makeTurn(turn);
        assertEquals(board.getFigure(Position.fromString("e1")).getClass(), Queen.class);
    }


}