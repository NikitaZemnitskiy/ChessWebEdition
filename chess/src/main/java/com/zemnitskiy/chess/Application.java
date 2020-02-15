package com.zemnitskiy.chess;

import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Component
@Slf4j
public class Application {

    @Autowired
    Game game;

    @Async
    public void startChess() {
        log.info("startChess");
        Scanner scanner = new Scanner(System.in);
        Position position1;
        Position position2;
        while (true) {
            try {
                String lastPosition = scanner.nextLine();
                position1 = new Position(lastPosition.charAt(0), Integer.parseInt(String.valueOf(lastPosition.charAt(1))));
                String newPosition = scanner.nextLine();
               position2 = new Position(newPosition.charAt(0), Integer.parseInt(String.valueOf(newPosition.charAt(1))));
            }
            catch (IllegalStateException e){
                System.out.println("Incorrect input");
                continue;
            }
                if (!game.makeTurn(position1, position2)){
                    System.out.println("Wrong turn");
                    continue;
                }

        }
    }

}
