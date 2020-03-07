package com.zemnitskiy.chess;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.boardRep.StandartBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAsync
@Slf4j
public class ChessApplication {

	@Autowired Application app;

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}

	@Bean
    Board board() {
		return StandartBoard.getStandartBoard();
	}

	@Bean
    Game game(Board board) {
		return new Game(board);
	}

	@PostConstruct
	void start() {
		app.startChess();
	}

}
