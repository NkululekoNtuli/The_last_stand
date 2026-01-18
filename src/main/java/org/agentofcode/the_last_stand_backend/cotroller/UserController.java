package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.model.Character;
import org.agentofcode.the_last_stand_backend.model.Hero;
import org.agentofcode.the_last_stand_backend.repository.UserRepository;
import org.agentofcode.the_last_stand_backend.service.HeroService;
import org.agentofcode.the_last_stand_backend.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.agentofcode.the_last_stand_backend.model.Users;
import org.agentofcode.the_last_stand_backend.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@SessionAttributes("name")
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/the-last-stand")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;
    private JwtService jwtService;
    private HeroService heroService;

    public UserController(UserService userService, UserRepository userRepository, JwtService jwtService, HeroService heroService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.heroService = heroService;
        this.jwtService = jwtService;
    }

// Do not forget to hash the password  from the front and from BL to DB
    @PostMapping(value = "/login")
    public ResponseEntity<?> user(@RequestBody Map<String, String> data){
        String userName = data.get("name");
        String userPassword = data.get("password");

        String token = null;
        if (userService.isValidUser(userName, userPassword)) {
            Users validUser = userService.getUserByName(userName);
            token = jwtService.generateToken(validUser.getUserName());
        }
        return ResponseEntity.ok(Map.of("token", token, "abilities", Character.abilityNames));
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> data) {
        String userName = data.get("name");
        String password = data.get("password");

        try{
            userService.registerUser(userName, password);
            return  ResponseEntity.ok(Map.of( "name",data.get("name")));
        }catch (Exception e){
            return ResponseEntity.status(401).body(Map.of("error", "UserName Already Taken!"));//ResponseEntity.ok(Map.of("result", "Username taken"));
        }
    }

    @PostMapping(value = "/heroes")
    public ResponseEntity<?> getUserHeroes(@RequestBody Map<String, String> data){
        String userName = data.get("ability");
        List<Hero> heroes = heroService.getHeroes(userService.getUserByName(userName).getId());
        System.out.println("num of hero: " + heroes.size());

        for (Hero hero : heroes){
            System.out.println("Hero: " + hero.toString() + "/n");
        }
        return ResponseEntity.ok(Map.of("heroes", heroService.getHeroes(userService.getUserByName(userName).getId())));
    }
}
