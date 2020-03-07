package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Turn;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public abstract class Figure {
  public static final Map<Character, Figure> boardMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<Character, Figure>('b', new Bishop(Color.BLACK)),
            new AbstractMap.SimpleEntry<Character, Figure>('B', new Bishop(Color.WHITE)),
            new AbstractMap.SimpleEntry<Character, Figure>('r', new Castle(Color.BLACK)),
            new AbstractMap.SimpleEntry<Character, Figure>('R', new Castle(Color.WHITE)),
            new AbstractMap.SimpleEntry<Character, Figure>('K', new King(Color.WHITE)),
            new AbstractMap.SimpleEntry<Character, Figure>('k', new King(Color.BLACK)),
            new AbstractMap.SimpleEntry<Character, Figure>('n', new Knight(Color.BLACK)),
            new AbstractMap.SimpleEntry<Character, Figure>('N', new Knight(Color.WHITE)),
            new AbstractMap.SimpleEntry<Character, Figure>('P', new Pawn(Color.WHITE)),
            new AbstractMap.SimpleEntry<Character, Figure>('p', new Pawn(Color.BLACK)),
            new AbstractMap.SimpleEntry<Character, Figure>('q', new Queen(Color.BLACK)),
            new AbstractMap.SimpleEntry<Character, Figure>('Q', new Queen(Color.WHITE))
    );


   /* static final Map<Character, Figure> boardMap = Map.of(
            'b', new Bishop(Color.BLACK), 'B', new Bishop(Color.WHITE),
            'r', new Castle(Color.BLACK), 'R', new Castle(Color.WHITE),
            'k', new King(Color.BLACK), 'K', new King(Color.WHITE),
            'n', new Knight(Color.BLACK), 'N', new Knight(Color.WHITE),
            'p', new Pawn(Color.BLACK), 'P', new Pawn(Color.WHITE));
    */


    public final Color color;

    public Figure(Color color) {
        this.color = color;
    }

    public abstract void isPossible(Turn turn, Board board);

    public Color getColor() {
        return color;
    }
}
