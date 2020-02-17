package com.zemnitskiy.chess;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Event;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.ChessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
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
    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String> builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-board")
                        .data(game.getBoard().toString())
                        .build());
    }

}