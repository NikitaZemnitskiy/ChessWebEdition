package com.zemnitskiy.chess;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ChessController {

    @Autowired
    Game game;
    @Autowired
    Application application;

    @GetMapping("/board")
    public String boardString () {
        Board board = game.getBoard();
        return board.draw();
    }
    @GetMapping("/turn")
    public String  makeTurn(@RequestParam(value = "turn", required = true, defaultValue = "")
                             String turn) {
        System.out.println(turn);
        System.out.println(turn.substring(0,turn.indexOf(" ")+1) +""+turn.substring(turn.indexOf(" ")+1));
        if (!application.makeTurn(turn.substring(0,turn.indexOf(" ")+1), turn.substring(turn.indexOf(" ")+1))){
            throw new WebServerException("WrongTurn", new IllegalStateException());
        }
        return "You make tour turn";
    }
 /*  @PostMapping("/turn")
    public void makeTurn2(@RequestBody String str){
       System.out.println(str);
   }*/

}