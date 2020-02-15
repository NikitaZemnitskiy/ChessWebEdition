package com.zemnitskiy.chess.domain;


import com.zemnitskiy.chess.domain.figures.Figure;
import lombok.Data;

@Data
public class Board {
    public Board(Figure[][] figures) {
        this.figures = figures;
    }

    Figure[][] figures;

    public String draw(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i<9; i++){
            for(int k = 1; k<9; k++){
                if(figures[i][k] != null) {
                    stringBuilder.append(figures[i][k]);
                }
                else {
                    stringBuilder.append(1);
                }
            }
        }
        return stringBuilder.toString();
    }
}
