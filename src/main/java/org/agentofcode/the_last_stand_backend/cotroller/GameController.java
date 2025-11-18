package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.model.Boss;
import org.agentofcode.the_last_stand_backend.model.Player;
import org.agentofcode.the_last_stand_backend.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        Player playerCharacter = new Player(name, skills.toArray(new String[0]));
        Boss Boss = new Boss();


        return new HashMap<>(Map.of(
                "name", playerCharacter.getName(),
                "hp", playerCharacter.getHealth(),
                "mp", playerCharacter.getMagicPower(),
                "level", playerCharacter.getLevel(),
                "skills", playerCharacter.getSkills()
        ));
    }

    @RequestMapping(value = "the-last-stand/game", method = RequestMethod.POST)
    public static HashMap<String, Object> executeAction(@RequestBody Map<String, Object> data) {

        String skillToExecute = data.get("skill").toString();
        String bossAction = gameService.executeSkill(skillToExecute);

        return new HashMap<String, Object>();
    }
}
