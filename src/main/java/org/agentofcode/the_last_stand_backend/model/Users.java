package org.agentofcode.the_last_stand_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Users {

    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @JsonIgnore
    private String password; // remember to hash password in db
//    private int rating;
//    private Instant created;

    public Users() {}

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
//        this.rating = rating;
//        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return name;
    }

//    public void setUserName(String userName) {
//        this.name = userName;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

//    public int getRating() {
//        return rating;
//    }

//    public void setRating(int rating) {
//        this.rating += rating;
//    }
//
//    public Instant getCreated() {
//        return created;
//    }
//
//    public void setCreated(Instant created) {
//        this.created = created;
//    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
