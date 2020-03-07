package com.zemnitskiy.chess.service;

import com.zemnitskiy.chess.GameType;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.boardRep.StandartBoard;
import com.zemnitskiy.chess.entity.GameEntity;
import com.zemnitskiy.chess.entity.GameRepository;
import com.zemnitskiy.chess.entity.TurnEntity;
import com.zemnitskiy.chess.entity.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GameService {
    Logger log = LoggerFactory.getLogger(GameService.class);
    @Autowired
    GameRepository gameRepository;

    @Autowired
    TurnRepository turnRepository;

    Set<GameEntity> activeGames = new HashSet<>();

    public GameEntity createGameEntity(MyUserPrincipal whitePlayer,  MyUserPrincipal blackPlayer){
        Board board = StandartBoard.getStandartBoard();
        log.info("Create new Game white player - {}, black player - {}", whitePlayer, blackPlayer);
        GameEntity gameEntity = new GameEntity (whitePlayer.getUser().getId(), blackPlayer.getUser().getId(), StandartBoard.getStandartBoard());
        gameEntity.setGameStatus("waitingForPlayers");
        gameEntity.setGame(new Game(board));
        activeGames.add(gameEntity);
        log.info("Game entity created - {}", gameEntity);
        System.out.println(gameEntity.getGameStatus());
        return gameRepository.save(gameEntity);
    }
    public GameEntity createGameEntity(MyUserPrincipal whitePlayer){
       return createGameEntity(whitePlayer, whitePlayer);
    }

    public void addTurn(MyUserPrincipal user, int gameId, String t){
        Position lastPos = Position.fromString(t.substring(0,2));
        Position newPos = Position.fromString(t.substring(2,4));
        Turn turn = new Turn(lastPos, newPos);

        GameEntity gameEntity = getGameEntityById(gameId);
        if(gameEntity == null){
            throw new IllegalStateException("Bad game id");
        }
        Game game = gameEntity.getGame();
        game.makeTurn(turn);
        gameEntity.setBoard(game.getBoard().toString());
        TurnEntity turnEntity = new TurnEntity(t,gameId);
        turnRepository.save(turnEntity);
        gameEntity.addTurn(turnEntity);
        gameRepository.save(gameEntity);
    }

    public GameEntity getGameEntityById(int gameId){
        for(GameEntity g: activeGames){
            if(g.getId() == gameId){
                return g;
            }
        }
            return gameRepository.findByGameId(gameId);
    }
}
