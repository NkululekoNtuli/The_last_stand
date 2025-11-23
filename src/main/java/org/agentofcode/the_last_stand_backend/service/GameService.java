package org.agentofcode.the_last_stand_backend.service;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.agentofcode.the_last_stand_backend.model.*;
import org.agentofcode.the_last_stand_backend.model.Character;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class GameService {

    private Player player;
    private Boss boss;
    private Dotenv dotenv = Dotenv.load();
    private String AI_API_KEY = dotenv.get("AI_API_KEY");
    private String AI_API_BASE_URL = dotenv.get("AI_API_URL");
    private ConcurrentHashMap<String, GameState> gameStates = new ConcurrentHashMap<>();

    public GameService() {
        boss = new Boss("Demon General", Character.abilities, 250, 0, 100);
    }

    public String promptAI() {
        ArrayList<String> bossSkills = new ArrayList<>();
        ArrayList<Ability>  testing = Character.abilities;
        testing.forEach(ability -> {
            bossSkills.add(ability.getName());
        });

        String prompt = "You are the boss in this situation, choose a skill to play based on the given information";
        String context = "This is a turn based game. player status is " + player.getCharacterInfo() +
                " and the boss status is " + boss.getCharacterInfo()  + "Respond only with the skill name.";

        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse(AI_API_BASE_URL)
                .newBuilder()
                .addQueryParameter("key", AI_API_KEY)
                .addQueryParameter("prompt", prompt)
                .addQueryParameter("context",  context)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String[] bodyComp = response.body().string().replace("}", "").split(":");
            String bossAttack = bodyComp[bodyComp.length - 1].replace("\"", "");

            return bossAttack;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void creatGameState( String userId, Character newPlayer){
        player = (Player) newPlayer;
        gameStates.put(userId, new GameState(player, boss));

    }


    public String gameIntro(){
        return "";
    }

    public Boss creatBoss(Player player){
        return new Boss();
    }

    public void executeSkill(String userId, Ability ability){
        GameState game = gameStates.get(userId);
        player = game.getPlayer();
        boss = game.getBoss();

        String move = promptAI();
        executeBossMove(move);
    }

    public String getBossLine() {
        return "";
    }

    public String getSkillInfo() {
        return "";
    }

    public String getOutro() {
        return "";
    }

    public void upgradeLevel(Character player){
        //upgrade level
    }

    public void concede() {
        //implement mp check
    }

    public void executeBossMove(String move) {
        //implement boss logic
        player.decreaseHealth(Character.abiltityMap.get(move).getPower());

    }

    public Map<String, Object> gameState(String userId){
        HashMap<String, Object> state = gameStates.get(userId).getGameState();
        Player player = (Player) state.get("player");
        Boss boss = (Boss) state.get("boss");

        return Map.of("playerName", player.getName(),
                "playerLevel", player.getLevel(),
                "playerHP", player.getHealth(),
                "playerMana", player.getMana(),
                "playerAbilities", player.getAbilities(),
                "enemyName", boss.getName(),
                "enemyLevel", boss.getLevel(),
                "enemyHP", boss.getHealth(),
                "enemyMana", boss.getMana(),
                "enemyAbilities", boss.getAbilities()
        );
    }

}
