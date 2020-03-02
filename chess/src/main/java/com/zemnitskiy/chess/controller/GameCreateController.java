package com.zemnitskiy.chess.controller;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.boardRep.StandartBoard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameCreateController {
    @GetMapping("/newSingleGame")
    public String newSingleGameCreator(){
        Game game = new Game(StandartBoard.getStandartBoard());

        return "registration";
    }
}
