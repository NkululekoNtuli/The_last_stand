package org.agentofcode.the_last_stand_backend.cotroller;

import org.springframework.web.bind.annotation.*;
import org.agentofcode.the_last_stand_backend.model.Users;
import org.agentofcode.the_last_stand_backend.service.UserService;

import java.util.Map;

@RestController
@SessionAttributes("name")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
        userService.registerUser(data.get("name"), data.get("password"));
        return userService.getUserByName(data.get("name")).toString();
    }
}
