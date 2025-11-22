package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.model.Ability;
import org.agentofcode.the_last_stand_backend.model.Boss;
import org.agentofcode.the_last_stand_backend.model.Character;
import org.agentofcode.the_last_stand_backend.model.Player;
import org.agentofcode.the_last_stand_backend.service.GameService;
import org.agentofcode.the_last_stand_backend.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.session.SessionSecurityMarker;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // implement cors config later
@RequestMapping(value = "/the-last-stand")
public class GameController {

    private GameService gameService;
    private JwtService jwtService;

    public GameController(GameService gameService, JwtService jwtService){
        this.gameService = gameService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/character/creation")
    public ResponseEntity<?> creatCharacter(@RequestBody Map<String, Object> data, @AuthenticationPrincipal String userId) {
        String name = data.get("name").toString();
        ArrayList<String> abilitiesNames = (ArrayList<String>) data.get("abilities");

        ArrayList<Ability>  abilities = new ArrayList<>();
        abilitiesNames.forEach(a -> {abilities.add(Character.abiltityMap.get(a));});

        Character playerCharacter = new Player(name, abilities, 100, 0, 50);
        gameService.creatGameState(userId, playerCharacter);

//        return gameService.gameState();
        return ResponseEntity.ok(gameService.gameState(userId));
    }

    @PostMapping(value = "/game")
    public ResponseEntity<?> executeAction(@RequestBody Map<String, Object> data, @AuthenticationPrincipal String userId ) {
        String abilityName = data.get("ability").toString();
        gameService.executeSkill(userId, Character.abiltityMap.get(abilityName));
        return ResponseEntity.ok(gameService.gameState(userId));
    }
}
