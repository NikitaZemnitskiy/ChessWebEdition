package com.zemnitskiy.chess.domain;

public class Position {
    private int vertical;
    private char horizontal;


    public Position(char horizontal, int vertical) {
        if(!isCorrect(horizontal,vertical)){
            throw new IllegalStateException("Incorrect horizontal or vertical");
        }
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public void setVertical(int vertical) {
        if(!isCorrect(vertical)){
            throw new IllegalStateException("Incorrect vertical");
        }
        this.vertical = vertical;
    }

    public void setHorizontal(char horizontal) {
        if(!isCorrect(horizontal)){
            throw new IllegalStateException("Incorrect horizontal");
        }
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public char getHorizontal() {
        return horizontal;
    }

    private boolean isCorrect(char vertical, int horizontal){
        if(isCorrect(vertical) && isCorrect(horizontal)){
            return true;
        }
        return false;
    }

    private boolean isCorrect(int vertical){
        if(vertical>0 && vertical<=8){
            return true;
        }
        return false;
    }

    private boolean isCorrect(char horizontal){

        switch (horizontal){
            case 'a':return true;
            case 'b':return true;
            case 'c':return true;
            case 'd':return true;
            case 'e':return true;
            case 'f':return true;
            case 'g':return true;
            case 'h':return true;
        }
        return false;
    }
    public int getIntHorizontal(){
        char h = getHorizontal();
        switch (h){
            case 'a':return 1;
            case 'b':return 2;
            case 'c':return 3;
            case 'd':return 4;
            case 'e':return 5;
            case 'f':return 6;
            case 'g':return 7;
            case 'h':return 8;
        }
        throw new IllegalStateException("Incorrect horizontal char");
    }

    @Override
    public String toString() {
        return "Position{" +
                "vertical=" + vertical +
                ", horizontal=" + horizontal +
                '}';
    }
}
