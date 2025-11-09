package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.service.GameService;
import org.springframework.web.bind.annotation.*;
import org.agentofcode.the_last_stand_backend.model.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @RequestMapping(value = "/character/creation", method = RequestMethod.POST)
    public static HashMap<String, Object> creatCharacter(@RequestBody Map<String, Object> data) {
        String name = data.get("name").toString();
        System.out.println(data.get("skills").getClass());
        ArrayList<String> skills = (ArrayList<String>) data.get("skills");

        Character character = new Character(name, skills.toArray(new String[0]));
        return new HashMap<>(Map.of(
                "name", character.getName(),
                "hp", character.getHealth(),
                "mp", character.getMagicPower(),
                "level", character.getLevel(),
                "skills", character.getSkills()
        ));
    }

    @RequestMapping(value = "the-last-stand/game", method = RequestMethod.POST)
    public static HashMap<String, Object> executeAction(@RequestBody Map<String, Object> data) {
        return new HashMap<String, Object>();
    }
}
