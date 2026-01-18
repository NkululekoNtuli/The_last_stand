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
    private int rating = 0;
//    private Instant created;

    public Users() {}

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
//        this.rating = 0;
//        this.created = created;
    }

    public Long getId() {
        return this.id;
    }

    public String getUserName() {
        return this.name;
    }


    public void updateRating(int rating) {
        this.rating += rating;
    }

    public String getPassword() {
        return this.password;
    }

    public int getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + this.id +
                ", userName='" + this.name + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }

//    public void setUserName(String userName) {
//        this.name = userName;
//    }

//    public void setPassword(String password) {
//        this.password = password;
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
}
