package com.zemnitskiy.chess;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.ChessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
@Slf4j
@RestController
public class ChessController {

    @Autowired
    Game game;
    @Autowired
    Application application;

    @GetMapping("/board")
    public String boardString () {
        Board board = game.getBoard();
        return board.toString();
    }


    @PostMapping("/turn")
    public ResponseEntity<Void> makeTurn(@RequestBody String turn){
        String lastPos = turn.substring(0,2);
        String newPos = turn.substring(2,4);
        log.info("Turn - " +lastPos + " " + newPos);
        application.makeTurn(lastPos, newPos);
        return ResponseEntity.noContent().build();
   }

}