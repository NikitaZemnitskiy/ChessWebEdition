package com.zemnitskiy.chess.domain;

import com.zemnitskiy.chess.domain.figures.Color;
import com.zemnitskiy.chess.domain.figures.King;
import com.zemnitskiy.chess.domain.figures.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void checkBoardFromString() {
        String strBoard = "rnbqkbnrpppppppp11111111111111111111111111111111PPPPPPPPRNBQKBNR";
        Board board = Board.getBoardFromString(strBoard);
        System.out.println(board.toString());
        assertEquals(strBoard, board.toString());
    }
    @Test
    void checkWhiteKingEat(){
       Board board = new Board();
       board.addFigureToBoard(Position.fromString("e4"), new King(Color.WHITE));
        board.addFigureToBoard(Position.fromString("b1"), new Queen(Color.BLACK));
        Turn expectedTurn = new Turn(Position.fromString("b1"), Position.fromString("e4"));
        assertEquals(board.tryToEatWhiteKing(), expectedTurn);

    }

    @Test
    void checkWhiteKingEat2(){
        Board board = new Board();
        board.addFigureToBoard(Position.fromString("e4"), new King(Color.WHITE));
        board.addFigureToBoard(Position.fromString("b1"), new Queen(Color.WHITE));
        Turn expectedTurn = new Turn(Position.fromString("b1"), Position.fromString("e4"));
        assertEquals(board.tryToEatWhiteKing(), null);
    }
    @Test
    void checkWhiteKingEat3(){
        Board board = Board.getStandartBoard();
        Turn expectedTurn = null;
        assertEquals(board.tryToEatWhiteKing(), expectedTurn);
    }
}