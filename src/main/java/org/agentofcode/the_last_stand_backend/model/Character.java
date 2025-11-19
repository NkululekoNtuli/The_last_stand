package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public interface Character {

//    int health = 0;
//    int level = 0;
//    int magicPower = 0 ;
//    String name = "";

    Map<String, Object> Skills = new HashMap<>(Map.ofEntries(
            Map.entry("Atomic Blast", Map.ofEntries(
                    Map.entry("Value", 22),
                    Map.entry("Type","Attack")
            )),             // High AoE burst
            Map.entry("Bleed", Map.ofEntries(
                    Map.entry("Value", 12),
                    Map.entry("Type","Attack")
            )),                    // DoT effect
            Map.entry("World Cutting Slash", Map.ofEntries(
                    Map.entry("Value", 28),
                    Map.entry("Type", "Attack")
            )),      // High single-target crit attack
            Map.entry("Laser Beam", Map.ofEntries(
                    Map.entry("Value", 18),
                    Map.entry("Type", "Attack")
            )),               // 18Precise mid–high damage
            Map.entry("Energy Blast", Map.ofEntries(
                            Map.entry("Value", 14),
                            Map.entry("Type", "Attack")
            )),             // Standard ranged attack
            Map.entry("Blind", 8),                     // Low damage + accuracy debuff
            Map.entry("Heal", 16),                     // Mid-tier heal
            Map.entry("Cleans", 10),
            Map.entry("Hell Fire", Map.ofEntries(
                            Map.entry("Value", 20),
                            Map.entry("Type", "Attack")
            )),                // Burning DoT + AoE
            Map.entry("Cleave", Map.ofEntries(
                    Map.entry("Value", 17),
                    Map.entry("Type", "Attack")
            )),                   // Multi-target physical slash
            Map.entry("Stun", 10),                     // Low damage + control effect
            Map.entry("Shadow Strike", Map.ofEntries(
                    Map.entry("Value", 15),
                    Map.entry("Type", "Attack")
            )),            // Fast strike ignoring some defense
            Map.entry("Frost Nova", Map.ofEntries(
                    Map.entry("Value", 16),
                    Map.entry("Type", "Attack")
            )),               // Mid damage + slow effect
            Map.entry("Dragon Roar", Map.ofEntries(
                    Map.entry("Value", 12),
                    Map.entry("Type", "Attack")
            )),              // Debuffs enemy attack
            Map.entry("Meteor Crash", Map.ofEntries(
                    Map.entry("Value", 25),
                    Map.entry("Type", "Attack")
            )),             // Heavy AoE impact
            Map.entry("Thunder Lance", Map.ofEntries(
                    Map.entry("Value", 18),
                    Map.entry("Type", "Attack")
            )),            // Lightning pierce damage
            Map.entry("Spirit Shield", Map.ofEntries(
                    Map.entry("Value", 14),
                    Map.entry("Type", "Attack")
            )),            // Damage reduction buff
            Map.entry("Rage Boost", Map.ofEntries(
                    Map.entry("Value", 10),
                    Map.entry("Type", "Attack")
            )),               // Increase own attack temporarily
            Map.entry("Blood Drain", Map.ofEntries(
                    Map.entry("Value", 18),
                    Map.entry("Type", "Attack")
            )),              // Damage + small heal
            Map.entry("Phoenix Feather", Map.ofEntries(
                    Map.entry("Value", 20),
                    Map.entry("Type", "Attack")
            )),          // Strong heal + burn cleanse
            Map.entry("Void Rend", Map.ofEntries(
                    Map.entry("Value", 26),
                    Map.entry("Type", "Attack")
            ))                 // Late-game high magic burst
    ));

    int getHealth();

    int getLevel();

    int getMagicPower();

    String getName();

    ArrayList<String> getSkills();

    void decreaseHealth(int damage);

    void decreaseMagicPower(int magicPower);

    void increaseHealth(int health);

    void increaseMagicPower(int magicPower);

    public HashMap<String, Object> getCharacterInfo();
}
