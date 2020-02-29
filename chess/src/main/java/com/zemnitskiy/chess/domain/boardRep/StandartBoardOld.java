package com.zemnitskiy.chess.domain.boardRep;


import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.figures.*;

public class StandartBoardOld {

    public static Board getStandartBoard(){
        Figure[][] standartSetup = new Figure[9][9];
        standartSetup[0][0] = new Castle(Color.WHITE);
        standartSetup[0][1] = new Knight(Color.WHITE);
        standartSetup[0][2] = new Bishop(Color.WHITE);
        standartSetup[0][3] = new Queen(Color.WHITE);
        standartSetup[0][4] = new King(Color.WHITE);
        standartSetup[0][5] = new Bishop(Color.WHITE);
        standartSetup[0][6] = new Knight(Color.WHITE);
        standartSetup[0][7] = new Castle(Color.WHITE);

        standartSetup[1][0] = new Pawn(Color.WHITE);
        standartSetup[1][1] = new Pawn(Color.WHITE);
        standartSetup[1][2] = new Pawn(Color.WHITE);
        standartSetup[1][3] = new Pawn(Color.WHITE);
        standartSetup[1][4] = new Pawn(Color.WHITE);
        standartSetup[1][5] = new Pawn(Color.WHITE);
        standartSetup[1][6] = new Pawn(Color.WHITE);
        standartSetup[1][7] = new Pawn(Color.WHITE);

        standartSetup[7][0] = new Castle(Color.BLACK);
        standartSetup[7][1] = new Knight(Color.BLACK);
        standartSetup[7][2] = new Bishop(Color.BLACK);
        standartSetup[7][3] = new Queen(Color.BLACK);
        standartSetup[7][4] = new King(Color.BLACK);
        standartSetup[7][5] = new Bishop(Color.BLACK);
        standartSetup[7][6] = new Knight(Color.BLACK);
        standartSetup[7][7] = new Castle(Color.BLACK);

        standartSetup[6][0] = new Pawn(Color.BLACK);
        standartSetup[6][1] = new Pawn(Color.BLACK);
        standartSetup[6][2] = new Pawn(Color.BLACK);
        standartSetup[6][3] = new Pawn(Color.BLACK);
        standartSetup[6][4] = new Pawn(Color.BLACK);
        standartSetup[6][5] = new Pawn(Color.BLACK);
        standartSetup[6][6] = new Pawn(Color.BLACK);
        standartSetup[6][7] = new Pawn(Color.BLACK);
        return new Board(standartSetup);
    }
}
