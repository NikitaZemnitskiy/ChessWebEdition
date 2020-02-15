package com.zemnitskiy.chess.domain.figures;


import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Figure {
   private Color color;
   private Position position;

   public Figure(Color color) {
      this.color = color;
   }

   public abstract boolean isPossible(Position position1, Position position2, Figure[][] figures);

}
