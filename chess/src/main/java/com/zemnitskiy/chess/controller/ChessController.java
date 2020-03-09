package com.zemnitskiy.chess.controller;
import com.zemnitskiy.chess.Application;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.service.GameService;
import com.zemnitskiy.chess.service.MyUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    Application application;
    @Autowired
    GameService gameService;


    AtomicLong eventCounter = new AtomicLong();

    @GetMapping("/boardPosition/{id}")
    public String boardString (@PathVariable("id") int gameId) {
        return gameService.getGameEntityById(gameId).getBoard();
      
    }


    @PostMapping("/turn/{id}")
    public ResponseEntity<Void> makeTurn(@PathVariable("id") int gameId, @RequestBody String turn, @AuthenticationPrincipal MyUserPrincipal user){
        gameService.addTurn(user.getUser(), gameId, turn);
        log.info("Turn - "+turn);
        return ResponseEntity.noContent().build();
   }
    @GetMapping("/stream-sse/{id}")
    public SseEmitter streamEvents(@PathVariable("id") int gameId) {
        Game game = gameService.getGameEntityById(gameId).getGame();
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
                    game.deleteObserver(this);
                }
            }
        };
        game.addObserver(observer);
        log.debug("Subscription created");
        return emitter;
    }


}