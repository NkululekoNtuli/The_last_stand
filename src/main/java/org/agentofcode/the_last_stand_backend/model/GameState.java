package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameState {
    private Player player;
    private Boss boss;


    public GameState() {}

    public GameState(Player player, Boss boss) {
        this.player = player;
        this.boss = boss;
    }

    public Player getPlayer() {
        return player;
    }

    public Boss getBoss() {
        return boss;
    }

    public HashMap<String, Object> getGameState(){
        HashMap<String, Object> state = new HashMap<>();
        state.put("playerName", player.getName());
        state.put("playerLevel", player.getLevel());
        state.put("playerHP", player.getHealth());
        state.put("playerMana", player.getMana());
        state.put("playerAbilities", player.getAbilities());
        state.put("playerMaxHP", player.getMaxHealth());
        state.put("playerMaxMana", player.getMaxMana());
        state.put("enemyName", boss.getName());
        state.put("enemyLevel", boss.getLevel());
        state.put("enemyHP", boss.getHealth());
        state.put("enemyMana", boss.getMana());
        state.put("enemyAbilities", boss.getAbilities());
        state.put("enemyAbilityUsed", boss.getAbilityUsed());
        state.put("enemyMaxHP", boss.getMaxHealth());
        state.put("enemyMaxMana", boss.getMaxMana());

        return state;
//        return HashMap.ofEntries(
//                Map.entry("playerName", player.getName()),
//                Map.entry("playerLevel", player.getLevel()),
//                Map.entry("playerHP", player.getHealth()),
//                Map.entry("playerMana", player.getMana()),
//                Map.entry("playerAbilities", player.getAbilities()),
//                Map.entry("playerMaxHP", player.getMaxHealth()),
//                Map.entry("playerMaxMana", player.getMaxMana()),
//                Map.entry("enemyName", boss.getName()),
//                Map.entry("enemyLevel", boss.getLevel()),
//                Map.entry("enemyHP", boss.getHealth()),
//                Map.entry("enemyMana", boss.getMana()),
//                Map.entry("enemyAbilities", boss.getAbilities()),
//                Map.entry("enemyAbilityUsed", boss.getAbilityUsed()),
//                Map.entry("enemyMaxHP", boss.getMaxHealth()),
//                Map.entry("enemyMaxMana", boss.getMaxMana())
//        );
    }

    @Override
    public String toString() {
        return "GameState{" +
                "player=" + player.getCharacterInfo() +
                ", boss=" + boss.getCharacterInfo() +
                '}';
    }
}
