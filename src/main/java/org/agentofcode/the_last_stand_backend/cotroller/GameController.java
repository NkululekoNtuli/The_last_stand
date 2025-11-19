package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.model.Boss;
import org.agentofcode.the_last_stand_backend.model.Character;
import org.agentofcode.the_last_stand_backend.model.Player;
import org.agentofcode.the_last_stand_backend.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {

    private static GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @RequestMapping(value = "/character/creation", method = RequestMethod.POST)
    public static HashMap<String, Object> creatCharacter(@RequestBody Map<String, Object> data) {
        String name = data.get("name").toString();
        System.out.println(data.get("skills").getClass());
        ArrayList<String> skills = (ArrayList<String>) data.get("skills");
        Character playerCharacter = new Player(name, skills, 100, 100, 50);
        gameService.insertPlayer(playerCharacter);
        System.out.println("player info: "+ playerCharacter.getCharacterInfo());

        return playerCharacter.getCharacterInfo();
    }

    @RequestMapping(value = "the-last-stand/game", method = RequestMethod.POST)
    public static HashMap<String, Object> executeAction(@RequestBody Map<String, Object> data) {


        gameService.promptAI();
        Map<String, Object> bossState = new HashMap<>();
        return gameService.gameState();
    }
}
