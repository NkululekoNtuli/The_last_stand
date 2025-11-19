package org.agentofcode.the_last_stand_backend.service;

//import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.agentofcode.the_last_stand_backend.model.Users;
import org.agentofcode.the_last_stand_backend.repository.UserRepository;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Users getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void registerUser(String name, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        Users user = new Users(name, hashedPassword);
        userRepository.save(user);
    }

    public boolean isValidUser(String userName, String password) {
        Users user = userRepository.findByUserName(userName);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

//    public List<Users> getUsersByRating(int rating) {
//        if (rating < 0) {rating = 1;}
//
//        return userRepository.findAllByRatingGreaterThan(rating);
//    }

    public void updateRating(int addition, String name) {
        Users user = userRepository.findByUserName(name);
        user.setRating(addition);
        userRepository.save(user);
    }
}
