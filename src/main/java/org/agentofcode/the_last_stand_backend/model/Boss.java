package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class Boss extends BaseCharacter {
    public Boss(){}
    public Boss(String name, ArrayList<Ability> abilities, int health, int level, int magicPower) {
        super(name, abilities, health, level, magicPower);
    }

    public void berserk(){
        // Something triggers the boss to go berserk this increases the bosses dmg and MP in exchange of some of its health
    }
}
