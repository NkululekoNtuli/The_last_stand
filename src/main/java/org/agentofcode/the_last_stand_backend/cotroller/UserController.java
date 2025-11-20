package org.agentofcode.the_last_stand_backend.cotroller;

import org.agentofcode.the_last_stand_backend.repository.UserRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.agentofcode.the_last_stand_backend.model.Users;
import org.agentofcode.the_last_stand_backend.service.UserService;
import org.sqlite.SQLiteException;

import java.util.Map;

@RestController
@SessionAttributes("name")
public class UserController {

    private final UserRepository userRepository;
    private UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

// Do not forget to hash the password  from the front and from BL to DB
    @RequestMapping(value = "/user-login", method = RequestMethod.POST)
    public String user(@RequestBody Map<String, String> data){
        String userName = data.get("name");
        String userPassword = data.get("password");

        if (userService.isValidUser(userName, userPassword)) {
            Users validUser = userService.getUserByName(userName);
            return "welcome back master " + validUser.getUserName();
        }else {
            return "Who are you?";
        }
    }

    @RequestMapping(value = "/user-registration", method = RequestMethod.POST)
    public String registerUser(@RequestBody Map<String, String> data) {
        String name = data.get("name");
        String password = data.get("password");
        System.out.println("name is: "+ name);
        System.out.println("password is :  "+ password);
//        Users users = new Users(userName, password);
//        System.out.println(userRepository.findUsersByName(name).getUserName());
        //            userRepository.findUsersByName(name);
        try{
            userService.registerUser(name, password);
            return userService.getUserByName(data.get("name")).toString();
        }catch (Exception e){
            return "USER NAME TAKEN";
        }
//        userService.registerUser(name, password);
//        return userService.getUserByName(data.get("name")).toString();
        //        if (userRepository.findUsersByName(name) != null ) return  "usertaken";//return ResponseEntity.status(400).body("Name already taken!");
    }
}
