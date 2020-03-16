package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;

public class Pawn extends Figure {
    private final int direction;
    public Pawn(Color color) {
        super(color);
        this.direction = color == Color.WHITE?1:-1;
    }

    @Override
    public void isPossible(Turn t, Board b) {

        if(isPossible1Up(t, b) || isPossible2Up(t,b) || isPossible1UP1Side(t,b)){
            transformIfPossible(t,b);
            return;
        }
        throw new WrongTurnException("Pawn can't make this " + t);
    }

    private boolean isPossible1Up(Turn turn, Board board){
        return !isFigureAtDestination(turn,board) && turn.dx == 0 && turn.dy == direction;
    }

    private boolean isPossible2Up(Turn turn, Board board){
        checkFigureBetweenTurn(turn,board);
        return (isTurnFirst(turn) && !isFigureAtDestination(turn, board) && turn.dx == 0 && turn.dy == 2 *direction);
    }

    private boolean isPossible1UP1Side(Turn turn, Board board){
        return isFigureAtDestination(turn, board) && Math.abs(turn.dx) == 1 && turn.dy == direction;
    }

    private void checkFigureBetweenTurn(Turn t, Board board){
        Figure figureAtDestination = board.figures[t.to.x][t.to.y];
        Figure firstOnPath = t.firstOnPath(board);
        if(firstOnPath == figureAtDestination){
            return;
        }
        throw new WrongTurnException("Pawn can't jump over other figure");
    }

    private boolean isFigureAtDestination(Turn turn, Board board){
        return board.getFigure(turn.to) != null;
    }

    private boolean isTurnFirst(Turn turn){
       return color == Color.WHITE && turn.from.y == 1 || color == Color.BLACK && turn.from.y == 6;

    }
    private void transformIfPossible(Turn turn, Board board){
        if(color == Color.WHITE && turn.to.y == 7 || color == Color.BLACK && turn.to.y ==0){
            board.figures[turn.from.x][turn.from.y] = new Queen(color);
        }
    }

    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "P";
        }
        return "p";
    }
}
