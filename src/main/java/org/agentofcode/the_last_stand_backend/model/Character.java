package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public interface Character {
    String damage = "Damage";
    int mana = 10;
    int coolD = 12;
    String aoe = "AOE";
    String cleans = "cleans";
    String debuff = "Debuff";
    String buff = "Buff";
    String cc = "Crowd Control";


    ArrayList<Ability> abilities = new ArrayList<>( List.of
            (
            new Ability("Atomic Blast", 22, aoe, mana, coolD),
            new Ability("Bleed", 12, damage, mana,coolD),
            new Ability("World Cutting Slash", 28, damage, mana, coolD),
            new Ability("Laser Beam", 18, damage, mana, coolD),
            new Ability("Energy Blast", 14, damage, mana, coolD),
            new Ability("Blind", 8, debuff, mana, coolD),
            new Ability("Heal", 16, cleans, mana, coolD),
            new Ability("Hell Fire", 20, aoe, mana, coolD),
            new Ability("Cleave", 17, aoe, mana, coolD),
            new Ability("Stun", 10, cc, mana, coolD),
            new Ability("Shadow Strike", 15, aoe, mana, coolD),
            new Ability("Frost Nova", 16, aoe, mana, coolD),
            new Ability("Dragon Roar", 12, debuff, mana, coolD),
            new Ability("Meteor Crash", 25, aoe, mana, coolD),
            new Ability("Thunder Lance", 18, damage, mana, coolD),
            new Ability("Spirit Shield", 14, buff, mana, coolD),
            new Ability("Rage Boost", 10, buff, mana, coolD),
            new Ability("Blood Drain", 13, damage, mana, coolD),
            new Ability("Phoenix Feather", 20, cleans, mana, coolD),
            new Ability("Void Rend", 26, damage, mana, coolD)
    ));

    Map<String, Ability> abiltityMap = abilities.stream()
            .collect(Collectors.toMap(Ability::getName, a -> a));

    ArrayList<String> abilityNames = abilities.stream()
            .map(Ability::getName)
            .collect(Collectors.toCollection(ArrayList::new));



    int getHealth();

    int getLevel();

    int getMana();

    String getName();

    ArrayList<Ability> getAbilities();

    void decreaseHealth(int damage);

    void decreaseMagicPower(int magicPower);

    void increaseHealth(int health);

    void increaseMagicPower(int magicPower);

    public HashMap<String, Object> getCharacterInfo();
}
