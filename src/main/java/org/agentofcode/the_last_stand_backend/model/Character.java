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
                    // fire
                    new Ability("Flame Burst", 18, damage, mana, coolD),
                    new Ability("Inferno Wave", 24, aoe, mana, coolD),
                    new Ability("Scorch Mark", 12, debuff, mana, coolD),
                    new Ability("Phoenix Rebirth", 20, cleans, mana, coolD),

                    // water ice
                    new Ability("Aqua Blade", 14, damage, mana, coolD),
                    new Ability("Tidal Crash", 22, aoe, mana, coolD),
                    new Ability("Frostbite", 16, debuff, mana, coolD),
                    new Ability("Glacial Prison", 10, cc, mana, coolD),

                    // earth
                    new Ability("Stone Fist", 15, damage, mana, coolD),
                    new Ability("Earthquake", 26, aoe, mana, coolD),
                    new Ability("Iron Skin", 14, buff, mana, coolD),
                    new Ability("Root Snare", 11, cc, mana, coolD),

                    // air
                    new Ability("Gale Slash", 17, damage, mana, coolD),
                    new Ability("Hurricane Spiral", 23, aoe, mana, coolD),
                    new Ability("Wind Shield", 14, buff, mana, coolD),
                    new Ability("Silence Gust", 10, debuff, mana, coolD),

                    // lighting
                    new Ability("Shock Bolt", 16, damage, mana, coolD),
                    new Ability("Storm Spear", 20, damage, mana, coolD),
                    new Ability("Chain Lightning", 25, aoe, mana, coolD),
                    new Ability("Static Charge", 12, debuff, mana, coolD),

                    // shadow
                    new Ability("Night Slash", 18, damage, mana, coolD),
                    new Ability("Umbral Nova", 24, aoe, mana, coolD),
                    new Ability("Fear Gaze", 10, cc, mana, coolD),
                    new Ability("Life Leech", 14, damage, mana, coolD),

                    // light
                    new Ability("Radiant Beam", 17, damage, mana, coolD),
                    new Ability("Holy Pulse", 22, aoe, mana, coolD),
                    new Ability("Blessing Aura", 14, buff, mana, coolD),
                    new Ability("Purify", 50, cleans, mana, coolD),

                    // arcane
                    new Ability("Arcane Missile", 16, damage, mana, coolD),
                    new Ability("Mana Rift", 20, debuff, mana, coolD),
                    new Ability("Astral Storm", 26, aoe, mana, coolD),
                    new Ability("Arcane Shield", 15, buff, mana, coolD)
            )
    );

    Map<String, Ability> abiltityMap = abilities.stream()
            .collect(Collectors.toMap(Ability::getName, a -> a));

    ArrayList<String> abilityNames = abilities.stream()
            .map(Ability::getName)
            .collect(Collectors.toCollection(ArrayList::new));


    int getHealth();

    int getLevel();

    int getMana();

    int getMaxHealth();

    int getMaxMana();

    String getName();

    ArrayList<Ability> getAbilities();

    void decreaseHealth(int damage);

    void decreaseMana(int magicPower);

    void increaseHealth(int health);

    void increaseMagicPower(int magicPower);

    void increaseMaxHealth(int health);

    void increaseMaxMana(int mana);

    public HashMap<String, Object> getCharacterInfo();
}
