package com.zemnitskiy.chess.entity;

import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.Turn;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table("games")
public class GameEntity {
    @Id
    int id;
    Game game;
    User whitePlayer;
    User blackPlayer;
    List<Turn> turns = new ArrayList<>();
    boolean isEnd;

    public GameEntity(Game game, User whitePlayer, User blackPlayer) {
        this.game = game;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.isEnd = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(User whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public User getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(User blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public GameEntity() {
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "id=" + id +
                ", game=" + game +
                ", whitePlayer=" + whitePlayer +
                ", blackPlayer=" + blackPlayer +
                ", turns=" + turns +
                ", isEnd=" + isEnd +
                '}';
    }
}
