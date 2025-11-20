package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.model.Ability;
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
        GameController.gameService = gameService;
    }

    @RequestMapping(value = "/character/creation", method = RequestMethod.POST)
    public static HashMap<String, Object> creatCharacter(@RequestBody Map<String, Object> data) {
        String name = data.get("name").toString();
        ArrayList<String> abilitiesNames = (ArrayList<String>) data.get("abilities");
        ArrayList<Ability>  abilities = new ArrayList<>();
        abilitiesNames.forEach(a -> {abilities.add(Character.abiltityMap.get(a));});
        Character playerCharacter = new Player(name, abilities, 100, 0, 50);
        gameService.insertPlayer(playerCharacter);
        System.out.println("player info: "+ playerCharacter.getCharacterInfo());

        return playerCharacter.getCharacterInfo();
    }

    @RequestMapping(value = "the-last-stand/game", method = RequestMethod.POST)
    public static HashMap<String, Object> executeAction(@RequestBody Map<String, Object> data) {
        String abilityName = data.get("ability").toString();
        gameService.executeSkill(Character.abiltityMap.get(abilityName));
        return gameService.gameState();
    }
}
