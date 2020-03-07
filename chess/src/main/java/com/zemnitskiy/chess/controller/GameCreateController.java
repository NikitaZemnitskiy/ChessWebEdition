package com.zemnitskiy.chess.controller;


import com.zemnitskiy.chess.GameType;
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
        User currentUser = user.getUser();
        gameService.createGameEntity(user);
        return "SingleGame";
    }
    @GetMapping("/turnTest")
    public void testTurn(@AuthenticationPrincipal MyUserPrincipal user){
        User currentUser = user.getUser();
        gameService.addTurn(user, 1, "e2e4");
    }
}
