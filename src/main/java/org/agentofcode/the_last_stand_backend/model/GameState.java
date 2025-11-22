package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

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
        state.put("player", player.getCharacterInfo());
        state.put("boss", boss.getCharacterInfo());
        return state;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "player=" + player.getCharacterInfo() +
                ", boss=" + boss.getCharacterInfo() +
                '}';
    }
}
