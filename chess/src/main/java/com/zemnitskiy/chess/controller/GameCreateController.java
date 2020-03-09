package com.zemnitskiy.chess.controller;

import com.zemnitskiy.chess.GameStatus;
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


    @GetMapping("/newSingleGame")
    public String newSingleGameCreator(@AuthenticationPrincipal MyUserPrincipal user){
        gameService.createGameEntity(user, user);
        return "SingleGame";
    }

    @GetMapping("/board/{boardId}")
    public String registration(@PathVariable("boardId") int boardId){
        return "forward:/board.html";
    }

    @PostMapping("/newGame")
    public String connectToGameOrCreateNew(@AuthenticationPrincipal MyUserPrincipal user){
        GameEntity gameEntity = gameService.connectToGameOrCreateNew(user);
            return "redirect:/board/"+gameEntity.getId();

    }

    @GetMapping("/turnTest")
    public String testTurn(@AuthenticationPrincipal MyUserPrincipal user){
        gameService.addTurn(user.getUser(), 3, "e7e5");
        return "SingleGame";
    }

   /* @GetMapping("/connect231")
    public String testConnectToTheGame(@RequestParam(name="id")@AuthenticationPrincipal MyUserPrincipal user, String id, Model model){
        model.addAttribute("id", id);
        gameService.ConnectToAnotherGame(user,Integer.parseInt(id));
        return "Connect";
    }
    @GetMapping("/freeGames")
    public String testViewOfFreeGames(@AuthenticationPrincipal MyUserPrincipal user){

      // gameService.ConnectToAnotherGame(user,id);
        return "freeGames";
    }
    @GetMapping("/getAllFreeGames")
    public String freeGames () {
        StringBuilder stringBuilder = new StringBuilder();
        Set<GameEntity> activeGames =gameService.getAllFreeGames();

       stringBuilder.append(activeGames.size());
       for (GameEntity gameEntity:activeGames){
           stringBuilder.append(gameEntity.getName());
           stringBuilder.append(" ");
       }
       return stringBuilder.toString();
    }*/
}
