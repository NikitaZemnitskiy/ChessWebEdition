package com.zemnitskiy.chess.domain;

import com.zemnitskiy.chess.domain.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 * This class construct from 2 Positions and contains all position between them
 *
 */

public class Turn implements Iterable<Position> {
    Logger log = LoggerFactory.getLogger(Turn.class);
    public final Position from;
    public final Position to;
    public int dx, dy;


    public Turn(Position from, Position to) {
        this.from = from;
        this.to = to;
        this.dx = to.x -from.x;
        this.dy = to.y -from.y;
  //      assert !(this.dx == 0 && this.dy == 0) : "Not a move";
    }

    /**
     *
     * @param turn(String view)
     *
     * @return new Turn
     *
     */
    public static Turn getTurnFromString(String turn){
        Position lastPos = Position.fromString(turn.substring(0, 2));
        Position newPos = Position.fromString(turn.substring(2, 4));
        return new Turn(lastPos, newPos);
    }

    public boolean isDiagonal() { return dx == dy || dx == -dy; }

    public boolean isVertical() {
        return dx == 0;
    }

    public boolean isHorizontal() {
        return dy == 0;
    }

    public boolean isCrazyMove(){
        return !(isDiagonal() || isVertical() || isHorizontal());
    }



    /**
     *
     * @param board
     * @return first figure on the Path of this turn. Take bord
     *
     */
   public Figure firstOnPath(Board board) {
        for (Position p : this) {
            Figure figure = board.getFigure(p);
            if (figure != null) return figure;
        }
        return null;
    }

    @Override
    public Iterator<Position> iterator() {
        return new TurnIterator();
    }

    final class TurnIterator implements Iterator<Position> {

        Position current;
        int stepX, stepY;

        TurnIterator() {
            this.current = Turn.this.from;
            this.stepX = Integer.compare(Turn.this.dx, 0);
            this.stepY = Integer.compare(Turn.this.dy, 0);
            log.debug("Iterator created, current position - {}, stepX - {}, step y - {}", current,stepX,stepY);
        }

        @Override
        public boolean hasNext() {
            return !current.equals(Turn.this.to);
        }

        @Override
        public Position next() {

            if(isCrazyMove()){
                current = Turn.this.to;
                return current;
            }

            current = current.moveBy(stepX, stepY);
            return current;
        }
    }
    public static String toE2E4Form(Turn turn){
        return turn.from + turn.to.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return from.equals(turn.from) &&
                to.equals(turn.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Turn{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
    public String toPositionString(){
        return from+to.toString();
    }

}
