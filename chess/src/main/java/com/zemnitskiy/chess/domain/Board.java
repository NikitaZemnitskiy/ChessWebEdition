package com.zemnitskiy.chess.domain;


import com.zemnitskiy.chess.domain.figures.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;


public class Board {
    private final Logger log = LoggerFactory.getLogger(Board.class);
    public Figure[][] figures = new Figure[8][8];

    public Board() {

    }
    public static Board getBoardFromString(String str){
        Map<Character, Figure> boardMap = Figure.boardMap;
        Figure[][] figures = new Figure[8][8];
        int count = 0;
        for(int i = 7; i>=0; i--){
            for(int j = 0;j<8; j++){
                if(boardMap.containsKey(str.charAt(count))){
                    figures[j][i] = boardMap.get(str.charAt(count));
                }
                count++;
            }
        }
        return new Board(figures);
    }
    public Board(Figure[][] figures) {
        this.figures = figures;
    }
    public Figure getFigure(Position p) {
        return figures[p.x][p.y];
    }
    public void addFigureToBoard(Position p, Figure figure){
        this.figures[p.x][p.y] = figure;
    }
    boolean hasFigure(Position p) {
        return  getFigure(p) != null;
    }

    public void makeTurn(Turn t){
        log.debug("Trying to make turn from {} to {} ",t.from, t.to);
        Figure currentFigure = getFigure(t.from);
        currentFigure.isPossible(t, this);
    }

    public static Board getStandartBoard(){
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


@Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<8; i++){
            for(int j = 7; j>=0; j--){
                if(figures[j][i] != null){
                    stringBuilder.append(figures[j][i]);
                }
                else {
                    stringBuilder.append(1);
                }
            }
        }
        return stringBuilder.reverse().toString();
    }

    public String toFEN(Game game){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<8; i++){
            int space = 0;
            for(int j = 7; j>=0; j--){
                if(figures[j][i] != null){
                    if(space != 0){
                        stringBuilder.append(space);
                        space = 0;
                    }
                    stringBuilder.append(figures[j][i]);
                }
                else {
                    space++;
                }
            }
            if(space != 0){
                stringBuilder.append(space);
            }
            stringBuilder.append("/");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() -1);
        stringBuilder = stringBuilder.reverse();
        if(game.isWhiteNow){
            stringBuilder.append(" "+"w");
        }
        else {
            stringBuilder.append(" "+"b");
        }
        stringBuilder.append(" KQkq - 0 1");
        return stringBuilder.toString();
    }


}
