package com.zemnitskiy.chess.controller;
import com.zemnitskiy.chess.Application;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
public class ChessController {

    @Autowired
    Game game;
    @Autowired
    Application application;


    AtomicLong eventCounter = new AtomicLong();

    @GetMapping("/boardPosition")
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
    public SseEmitter streamEvents() {
        final SseEmitter emitter = new SseEmitter();
        final Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                String boardState = (String) arg;
                log.debug("Event fired: " + boardState);
                long eventId = eventCounter.incrementAndGet();

                try {
                    emitter.send(SseEmitter.event().name("boardUpdate").data(game.getBoard().toString()).id(String.valueOf(eventId)));
                } catch (IOException e) {
                    log.warn("Cannot send event to client, unsubscribe observer");
                    ChessController.this.game.deleteObserver(this);
                }
            }
        };
        this.game.addObserver(observer);
        log.debug("Subscription created");
        return emitter;
    }


}