package com.zemnitskiy.chess.controller;

import com.zemnitskiy.chess.entity.GameEntity;
import com.zemnitskiy.chess.service.GameService;
import com.zemnitskiy.chess.service.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameCreateController {

    @Autowired
    GameService gameService;

    @GetMapping("/board/{boardId}")
    public String registration(@PathVariable("boardId") int boardId){
        return "forward:/board.html";
    }

    @PostMapping("/newGame")
    public String connectToGameOrCreateNew(@AuthenticationPrincipal MyUserPrincipal user){
        GameEntity gameEntity = gameService.connectToGameOrCreateNew(user);
        return "redirect:/board/" + gameEntity.getId();
    }
}
