package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.model.Ability;
import org.agentofcode.the_last_stand_backend.model.Character;
import org.agentofcode.the_last_stand_backend.model.Hero;
import org.agentofcode.the_last_stand_backend.model.Users;
import org.agentofcode.the_last_stand_backend.repository.HeroRepository;
import org.agentofcode.the_last_stand_backend.repository.UserRepository;
import org.agentofcode.the_last_stand_backend.service.GameService;
import org.agentofcode.the_last_stand_backend.service.JwtService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // implement cors config later
@RequestMapping(value = "/the-last-stand")
public class GameController {

    private GameService gameService;
    private JwtService jwtService;
    private HeroRepository heroRepository;
    private UserRepository userRepository;

    public GameController(GameService gameService, JwtService jwtService, HeroRepository heroRepository, UserRepository userRepository){
        this.gameService = gameService;
        this.jwtService = jwtService;
        this.heroRepository = heroRepository;
        this.userRepository = userRepository;
    }

//    @PostMapping(value = "/character/creation")
//    public ResponseEntity<?> creatCharacter(@RequestBody Map<String, Object> data, @AuthenticationPrincipal String userId) {
//        String name = data.get("name").toString();
//        ArrayList<String> abilitiesNames = (ArrayList<String>) data.get("abilities");
//
//        ArrayList<Ability>  abilities = new ArrayList<>();
//        abilitiesNames.forEach(a -> {abilities.add(Character.abiltityMap.get(a));});
//
//        Character playerCharacter = new Hero(name, abilities, 300, 0, 150, 0, 0);
//        gameService.creatGameState(userId, heroRepository.findHeroByName(name));
//
//        return ResponseEntity.ok(gameService.gameState(userId));
//
//    }

    @PostMapping(value = "/start_game")
    public ResponseEntity<?> startGame(@RequestBody Map<String, Object> data, @AuthenticationPrincipal String userId) {
        String heroName = data.get("heroName").toString();
        String userName = data.get("userName").toString();
        Users user = userRepository.findUsersByName(userName);
        Hero hero = heroRepository.findHeroesByUserIdAndName((Long) user.getId(), heroName);
        gameService.creatGameState(userId, hero, userName);
        return ResponseEntity.ok(gameService.gameState(userId));
    }


    @PostMapping(value = "/save_hero")
    public ResponseEntity<?> saveHero(@RequestBody Map<String, Object> data, @AuthenticationPrincipal String userId) {
        String userName = data.get("username").toString();
        String heroName = data.get("heroName").toString();
        ArrayList<String> abilitiesNames = (ArrayList<String>) data.get("abilities");

        ArrayList<Ability>  abilities = new ArrayList<>();
        abilitiesNames.forEach(a -> {abilities.add(Character.abiltityMap.get(a));});

        long playerId = userRepository.findUsersByName(userName).getId();
        Character hero = new Hero(heroName, abilities, 300, 0, 150, 0, 0, playerId);
        System.out.println("HELLO");
        heroRepository.save((Hero) hero);
        System.out.println("hero saved");
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }



    @PostMapping(value = "/game")
    public ResponseEntity<?> executeAction(@RequestBody Map<String, Object> data, @AuthenticationPrincipal String userId ) {
        String abilityName = data.get("ability").toString();
        gameService.executeSkill(userId, Character.abiltityMap.get(abilityName));
        return ResponseEntity.ok(gameService.gameState(userId));
    }

    @PostMapping(value = "/endGame")
    public ResponseEntity<?> endGame(@RequestBody Map<String, Object> data, @AuthenticationPrincipal String userId) {
        gameService.endGame(userId);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

}
