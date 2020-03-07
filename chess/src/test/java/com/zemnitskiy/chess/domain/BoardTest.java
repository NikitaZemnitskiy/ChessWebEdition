package com.zemnitskiy.chess.domain;

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
}