package com.zemnitskiy.chess.domain;


import com.zemnitskiy.chess.domain.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Board {
    Logger log = LoggerFactory.getLogger(Board.class);

    public Figure[][] figures = new Figure[8][8];

    public Board() {

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


@Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<8; i++){
            for(int n = 7; n>=0; n--){
                if(figures[n][i] != null){
                    stringBuilder.append(figures[n][i]);
                }
                else {
                    stringBuilder.append(1);
                }
            }
        }
        return stringBuilder.reverse().toString();
    }

}
