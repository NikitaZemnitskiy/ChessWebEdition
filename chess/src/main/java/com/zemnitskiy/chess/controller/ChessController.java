package com.zemnitskiy.chess.controller;
import com.zemnitskiy.chess.Application;
import com.zemnitskiy.chess.GameStatus;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.entity.GameEntity;
import com.zemnitskiy.chess.entity.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    AtomicLong eventCounter = new AtomicLong();

    @GetMapping("/boardPosition/{id}")
    public String boardString (@PathVariable("id") int gameId) {
        return gameService.getGameEntityById(gameId).getBoard();
    }

    @GetMapping("/players/{id}")
    public String getPlayers (@PathVariable("id") int gameId) {
        Game.Player black = gameService.getGameEntityById(gameId).getGame().black;
        Game.Player white = gameService.getGameEntityById(gameId).getGame().white;

       StringBuilder str = new StringBuilder();
       str.append(white.name);
        System.out.println(black);
       if(black != null){
           str.append(" VS ");
           str.append(black.name);
       }
       log.debug(str.toString());
       return str.toString();
    }

    @GetMapping("/status/{id}")
    public String getStatus (@PathVariable("id") int gameId) {
        return gameService.getGameEntityById(gameId).getGameStatus().toString();
    }



    @PostMapping("/turn/{id}")
    public ResponseEntity<Void> makeTurn(@PathVariable("id") int gameId, @RequestBody String turn, @AuthenticationPrincipal MyUserPrincipal user){
        gameService.addTurn(user.getUser(), gameId, turn);
        log.info("Turn - "+turn);
        return ResponseEntity.noContent().build();
   }
    @PostMapping("/surrendered/{id}")
    public void surrendered(@PathVariable("id") int gameId, @AuthenticationPrincipal MyUserPrincipal user){
        System.out.println("SURRENDED ! " + user + gameId);
            gameService.addSurrendered(user, gameId);
    }

    @GetMapping("/stream-sse/{id}")
    public SseEmitter streamEvents(@PathVariable("id") int gameId) {
        GameEntity gameEntity = gameService.getGameEntityById(gameId);
        Game game = gameEntity.getGame();
        final SseEmitter emitter = new SseEmitter();
        final Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                long eventId = eventCounter.incrementAndGet();
                Game.Event event = (Game.Event) arg;
                log.debug("Event fired: " + event);

                try {
                        emitter.send(SseEmitter.event().name(event.eventType).data(event.data).id(String.valueOf(eventId)));
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