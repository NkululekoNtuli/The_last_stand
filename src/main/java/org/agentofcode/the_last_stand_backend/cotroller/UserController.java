package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.repository.UserRepository;
import org.agentofcode.the_last_stand_backend.service.JwtService;
import org.hibernate.NonUniqueResultException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.agentofcode.the_last_stand_backend.model.Users;
import org.agentofcode.the_last_stand_backend.service.UserService;
import org.sqlite.SQLiteException;

import java.util.HashMap;
import java.util.Map;

@RestController
@SessionAttributes("name")
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/the-last-stand")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;
    private JwtService jwtService;

    public UserController(UserService userService, UserRepository userRepository, JwtService jwtService) {
        this.userService = userService;
        this.userRepository = userRepository;
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
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> data) {
        String name = data.get("name");
        String password = data.get("password");
        System.out.println("name is: "+ name);
        System.out.println("password is :  "+ password);
        try{
            userService.registerUser(name, password);
            return  ResponseEntity.ok(data.get("name"));//userService.getUserByName(data.get("name")).toString();
        }catch (Exception e){
            return ResponseEntity.ok(Map.of("result", "Username taken"));
        }
    }
}
