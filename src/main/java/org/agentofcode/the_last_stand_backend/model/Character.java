package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public interface Character {

//    int health = 0;
//    int level = 0;
//    int magicPower = 0 ;
//    String name = "";

//    Map<String, Object> Abilitys = new HashMap<>(Map.ofEntries(
//            Map.entry("Atomic Blast", Map.ofEntries(
//                    Map.entry("Value", 22),
//                    Map.entry("Type","Attack")
//            )),             // High AoE burst
//            Map.entry("Bleed", Map.ofEntries(
//                    Map.entry("Value", 12),
//                    Map.entry("Type","Attack")
//            )),                    // DoT effect
//            Map.entry("World Cutting Slash", Map.ofEntries(
//                    Map.entry("Value", 28),
//                    Map.entry("Type", "Attack")
//            )),      // High single-target crit attack
//            Map.entry("Laser Beam", Map.ofEntries(
//                    Map.entry("Value", 18),
//                    Map.entry("Type", "Attack")
//            )),               // 18Precise mid–high damage
//            Map.entry("Energy Blast", Map.ofEntries(
//                            Map.entry("Value", 14),
//                            Map.entry("Type", "Attack")
//            )),             // Standard ranged attack
//            Map.entry("Blind", 8),                     // Low damage + accuracy debuff
//            Map.entry("Heal", 16),                     // Mid-tier heal
//            Map.entry("Cleans", 10),
//            Map.entry("Hell Fire", Map.ofEntries(
//                            Map.entry("Value", 20),
//                            Map.entry("Type", "Attack")
//            )),                // Burning DoT + AoE
//            Map.entry("Cleave", Map.ofEntries(
//                    Map.entry("Value", 17),
//                    Map.entry("Type", "Attack")
//            )),                   // Multi-target physical slash
//            Map.entry("Stun", 10),                     // Low damage + control effect
//            Map.entry("Shadow Strike", Map.ofEntries(
//                    Map.entry("Value", 15),
//                    Map.entry("Type", "Attack")
//            )),            // Fast strike ignoring some defense
//            Map.entry("Frost Nova", Map.ofEntries(
//                    Map.entry("Value", 16),
//                    Map.entry("Type", "Attack")
//            )),               // Mid damage + slow effect
//            Map.entry("Dragon Roar", Map.ofEntries(
//                    Map.entry("Value", 12),
//                    Map.entry("Type", "Attack")
//            )),              // Debuffs enemy attack
//            Map.entry("Meteor Crash", Map.ofEntries(
//                    Map.entry("Value", 25),
//                    Map.entry("Type", "Attack")
//            )),             // Heavy AoE impact
//            Map.entry("Thunder Lance", Map.ofEntries(
//                    Map.entry("Value", 18),
//                    Map.entry("Type", "Attack")
//            )),            // Lightning pierce damage
//            Map.entry("Spirit Shield", Map.ofEntries(
//                    Map.entry("Value", 14),
//                    Map.entry("Type", "Attack")
//            )),            // Damage reduction buff
//            Map.entry("Rage Boost", Map.ofEntries(
//                    Map.entry("Value", 10),
//                    Map.entry("Type", "Attack")
//            )),               // Increase own attack temporarily
//            Map.entry("Blood Drain", Map.ofEntries(
//                    Map.entry("Value", 18),
//                    Map.entry("Type", "Attack")
//            )),              // Damage + small heal
//            Map.entry("Phoenix Feather", Map.ofEntries(
//                    Map.entry("Value", 20),
//                    Map.entry("Type", "Attack")
//            )),          // Strong heal + burn cleanse
//            Map.entry("Void Rend", Map.ofEntries(
//                    Map.entry("Value", 26),
//                    Map.entry("Type", "Attack")
//            ))                 // Late-game high magic burst
//    ));
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
            .collect(Collectors.toMap(Ability::getName, s -> s));


    int getHealth();

    int getLevel();

    int getMagicPower();

    String getName();

    ArrayList<Ability> getAbilities();

    void decreaseHealth(int damage);

    void decreaseMagicPower(int magicPower);

    void increaseHealth(int health);

    void increaseMagicPower(int magicPower);

    public HashMap<String, Object> getCharacterInfo();
}
