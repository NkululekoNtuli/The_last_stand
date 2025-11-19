package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

@Component
public abstract class Skill {
    public String skillName;
    public int value;

    public Skill(){}

    public Skill(int value){
        this.value = value;
    }
}
