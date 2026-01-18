package org.agentofcode.the_last_stand_backend.service;

//import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.agentofcode.the_last_stand_backend.model.Users;
import org.agentofcode.the_last_stand_backend.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Users getUserByName(String name) {
        return userRepository.findUsersByName(name);
    }

    public void registerUser(String name, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        Users user = new Users(name, hashedPassword);
        System.out.println("user name is :u"+user.getUserName() + " and password is :" + user.getPassword());
        userRepository.save(user);
    }

    public boolean isValidUser(String name, String password) {
        Users user = userRepository.findUsersByName(name);
//        System.out.println(user.getUserName());
        if (user == null) {
            System.out.println("....");
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

//    public List<Users> getUsersByRating(int rating) {
//        if (rating < 0) {rating = 1;}
//
//        return userRepository.findAllByRatingGreaterThan(rating);
//    }

    @Transactional
    public void updateRating(int addition, String name) {
        Users user = userRepository.findUsersByName(name);
        user.updateRating(addition);
        userRepository.save(user);
    }
}
