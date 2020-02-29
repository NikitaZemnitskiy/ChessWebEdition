package com.zemnitskiy.chess.domain.boardRep;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.figures.*;

public class StandartBoard {
    public static Board getStandartBoard() {
        Board board = new Board();
        board.addFigureToBoard(Position.fromString("a1"), new Castle(Color.WHITE));
        board.addFigureToBoard(Position.fromString("b1"), new Knight(Color.WHITE));
        board.addFigureToBoard(Position.fromString("c1"), new Bishop(Color.WHITE));
        board.addFigureToBoard(Position.fromString("d1"), new Queen(Color.WHITE));
        board.addFigureToBoard(Position.fromString("e1"), new King(Color.WHITE));
        board.addFigureToBoard(Position.fromString("f1"), new Bishop(Color.WHITE));
        board.addFigureToBoard(Position.fromString("g1"), new Knight(Color.WHITE));
        board.addFigureToBoard(Position.fromString("h1"), new Castle(Color.WHITE));

        board.addFigureToBoard(Position.fromString("a2"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("b2"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("c2"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("d2"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("e2"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("f2"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("g2"), new Pawn(Color.WHITE));
        board.addFigureToBoard(Position.fromString("h2"), new Pawn(Color.WHITE));

        board.addFigureToBoard(Position.fromString("a7"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("b7"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("c7"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("d7"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("e7"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("f7"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("g7"), new Pawn(Color.BLACK));
        board.addFigureToBoard(Position.fromString("h7"), new Pawn(Color.BLACK));

        board.addFigureToBoard(Position.fromString("a8"), new Castle(Color.BLACK));
        board.addFigureToBoard(Position.fromString("b8"), new Knight(Color.BLACK));
        board.addFigureToBoard(Position.fromString("c8"), new Bishop(Color.BLACK));
        board.addFigureToBoard(Position.fromString("d8"), new Queen(Color.BLACK));
        board.addFigureToBoard(Position.fromString("e8"), new King(Color.BLACK));
        board.addFigureToBoard(Position.fromString("f8"), new Bishop(Color.BLACK));
        board.addFigureToBoard(Position.fromString("g8"), new Knight(Color.BLACK));
        board.addFigureToBoard(Position.fromString("h8"), new Castle(Color.BLACK));



        return board;
    }
}
