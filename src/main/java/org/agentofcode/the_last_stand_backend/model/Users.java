package org.agentofcode.the_last_stand_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Users {

    @Id @GeneratedValue
    private Long id;
    private String userName;
    private String password; // remember to hash password in db
    private int rating;
    private Instant created;

    public Users() {}
    public Users(String userName, String password, int rating, Instant created) {
        this.userName = userName;
        this.password = password;
        this.rating = rating;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating += rating;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Users{" +
                "created=" + created +
                ", rating=" + rating +
                ", userName='" + userName + '\'' +
                '}';
    }
}
