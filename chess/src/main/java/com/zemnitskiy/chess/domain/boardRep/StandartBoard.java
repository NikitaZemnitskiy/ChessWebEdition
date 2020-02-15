package com.zemnitskiy.chess.domain.boardRep;


import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.figures.*;

public class StandartBoard {

    public static Board getStandartBoard(){
        Figure[][] standartSetup = new Figure[9][9];
        standartSetup[1][1] = new Rook(Color.WHITE);
        standartSetup[1][2] = new Knight(Color.WHITE);
        standartSetup[1][3] = new Bishop(Color.WHITE);
        standartSetup[1][4] = new Queen(Color.WHITE);
        standartSetup[1][5] = new King(Color.WHITE);
        standartSetup[1][6] = new Bishop(Color.WHITE);
        standartSetup[1][7] = new Knight(Color.WHITE);
        standartSetup[1][8] = new Rook(Color.WHITE);

        standartSetup[2][1] = new Pawn(Color.WHITE);
        standartSetup[2][2] = new Pawn(Color.WHITE);
        standartSetup[2][3] = new Pawn(Color.WHITE);
        standartSetup[2][4] = new Pawn(Color.WHITE);
        standartSetup[2][5] = new Pawn(Color.WHITE);
        standartSetup[2][6] = new Pawn(Color.WHITE);
        standartSetup[2][7] = new Pawn(Color.WHITE);
        standartSetup[2][8] = new Pawn(Color.WHITE);

        standartSetup[8][1] = new Rook(Color.BLACK);
        standartSetup[8][2] = new Knight(Color.BLACK);
        standartSetup[8][3] = new Bishop(Color.BLACK);
        standartSetup[8][4] = new King(Color.BLACK);
        standartSetup[8][5] = new Queen(Color.BLACK);
        standartSetup[8][6] = new Bishop(Color.BLACK);
        standartSetup[8][7] = new Knight(Color.BLACK);
        standartSetup[8][8] = new Rook(Color.BLACK);

        standartSetup[7][1] = new Pawn(Color.BLACK);
        standartSetup[7][2] = new Pawn(Color.BLACK);
        standartSetup[7][3] = new Pawn(Color.BLACK);
        standartSetup[7][4] = new Pawn(Color.BLACK);
        standartSetup[7][5] = new Pawn(Color.BLACK);
        standartSetup[7][6] = new Pawn(Color.BLACK);
        standartSetup[7][7] = new Pawn(Color.BLACK);
        standartSetup[7][8] = new Pawn(Color.BLACK);
        return new Board(standartSetup);
    }
}
