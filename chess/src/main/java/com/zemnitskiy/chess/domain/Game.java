package com.zemnitskiy.chess.domain;


import com.zemnitskiy.chess.GameStatus;
import com.zemnitskiy.chess.domain.exceptions.NotYourTurnException;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;
import com.zemnitskiy.chess.domain.figures.Color;
import com.zemnitskiy.chess.domain.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Observable;

public class Game extends Observable {
    Logger log = LoggerFactory.getLogger(Game.class);
    final Board board;
    public Player white, black;
    public boolean isWhiteNow = true;
    public GameStatus status;

    public Game(Board board) {
        this.board = board;
        this.status = null;
    }

    public void startGame(Player white) {
        log.debug("Player "+white +" create game" );
        this.white = white;
        setChanged();
        setStatus(GameStatus.WAITING);
        setChanged();
        setPlayers();
    }

    public void joinGame(Player black) {
        log.debug("Player "+black +" join to Player "+white );
        this.black = black;
        setChanged();
        setStatus(GameStatus.GAME);
        setChanged();
        setPlayers();
    }
    public void addSurrendered(String name) {
       if(name.equals(white.name)){
           setChanged();
           setStatus(GameStatus.BLACK_WIN);
       }
       else if(name.equals(black.name)) {
           setChanged();
           setStatus(GameStatus.WHITE_WIN);
       }
       else {
           log.debug("Someone unknown want to gave up");
       }
    }


    private void setStatus(GameStatus status) {
        setChanged();
        this.status = status;
        notifyObservers(new Event("statusUpdated", this.status.name()));
    }
    private void setPlayers(){
        if(black == null)
        notifyObservers(new Event("playersUpdated", white.name));
        else notifyObservers(new Event("playersUpdated", white.name + " VS" + black.name));
    }

    public void makeTurn(Turn turn){

            board.makeTurn(turn);
            board.figures[turn.to.x][turn.to.y] = board.figures[turn.from.x][turn.from.y];
            board.figures[turn.from.x][turn.from.y] = null;

        log.debug(turn + " made");
        isWhiteNow = !isWhiteNow;
        setChanged();
        log.debug("notifyObservers {}", countObservers());
        notifyObservers(new Event("boardUpdated", this.board.toString()));
        setChanged();
        Figure figureUpdated = board.getFigure(turn.to);
        notifyObservers(new Event("turnUpdated", figureUpdated+Turn.toE2E4Form(turn)));
        if(!board.toString().contains("K") && !board.toString().contains("k")){
            setStatus(GameStatus.DRAW);
        }
        else if(!board.toString().contains("K")){
            setStatus(GameStatus.BLACK_WIN);
        }
        else if(!board.toString().contains("k")){
            setStatus(GameStatus.WHITE_WIN);
        }
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                ", white=" + white +
                ", black=" + black +
                ", isWhiteNow=" + isWhiteNow +
                ", status=" + status +
                '}';
    }

    public static class Event implements Serializable {
        public final String eventType;
        public final String data;

        public Event(String eventType, String data) {
            this.eventType = eventType;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "eventType='" + eventType + '\'' +
                    ", data='" + data + '\'' +
                    '}';
        }
    }

    public static class Player {
        public final String name;

        public Player(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
