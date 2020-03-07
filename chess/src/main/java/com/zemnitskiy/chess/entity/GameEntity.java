package com.zemnitskiy.chess.entity;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.boardRep.StandartBoard;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table("game")
public class GameEntity {
    @Id
    int id;
    @Column("white_player_id")
    int whitePlayer;
    @Column("black_player_id")
    int blackPlayer;
    @Column("board")
    String board;
    @Transient
    Game game;

    @MappedCollection(idColumn = "id")
    Set<TurnEntity> turns = new HashSet<>();

    @Column("status")
    String gameStatus;

    public GameEntity(int whitePlayer, int blackPlayer, Board board) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board.toString();
        this.game = new Game(board);
        this.gameStatus = "waitingForPlayers";
    }
    public void addTurn(TurnEntity turnEntity){
        turns.add(turnEntity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(int whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public int getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(int blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Set<TurnEntity> getTurns() {
        return turns;
    }

    public void setTurns(Set<TurnEntity> turns) {
        this.turns = turns;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameEntity() {
    }


    @Override
    public String toString() {
        return "GameEntity{" +
                "id=" + id +
                ", whitePlayer=" + whitePlayer +
                ", blackPlayer=" + blackPlayer +
                ", board='" + board + '\'' +
                ", game=" + game +
                ", turns=" + turns +
                ", gameStatus='" + gameStatus + '\'' +
                '}';
    }
}
