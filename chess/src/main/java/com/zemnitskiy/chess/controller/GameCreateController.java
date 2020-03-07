package com.zemnitskiy.chess.controller;



import com.zemnitskiy.chess.entity.*;
import com.zemnitskiy.chess.service.GameService;
import com.zemnitskiy.chess.service.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameCreateController {

    @Autowired
    GameService gameService;


    @GetMapping("/newSingleGame")
    public String newSingleGameCreator(@AuthenticationPrincipal MyUserPrincipal user){
        gameService.createGameEntity(user, user);
        return "SingleGame";
    }

    @GetMapping("/newGame")
    public String newGameCreator(@AuthenticationPrincipal MyUserPrincipal user){
        gameService.createGameEntityForTwoPlayers(user);
        return "NewGame";
    }

    @GetMapping("/turnTest")
    public String testTurn(@AuthenticationPrincipal MyUserPrincipal user){
        gameService.addTurn(user.getUser(), 3, "e7e5");
        return "SingleGame";
    }
}
