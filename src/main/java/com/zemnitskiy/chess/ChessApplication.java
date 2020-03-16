package com.zemnitskiy.chess;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAsync
@Slf4j
public class ChessApplication {

	@Autowired
	GameService gameService;

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}

	@Bean
    Board board() {
		return Board.getStandardBoard();
	}

	@Bean
    Game game(Board board) {
		return new Game(board);
	}

	@PostConstruct
	void start() {
		gameService.downloadGamesFromDataBase();
		gameService.deleteAwaitGames();
	}

}
