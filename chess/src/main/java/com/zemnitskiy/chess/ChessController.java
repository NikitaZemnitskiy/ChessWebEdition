package com.zemnitskiy.chess;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChessController {

    @Autowired
    Game game;

    @GetMapping("/board")
    public String boardString () {
        Board board = game.getBoard();
        return board.draw();
    }

}