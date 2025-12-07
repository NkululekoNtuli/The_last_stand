package org.agentofcode.the_last_stand_backend.service;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.agentofcode.the_last_stand_backend.model.*;
import org.agentofcode.the_last_stand_backend.model.Character;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
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
        boss = new Boss("Demon General", Character.abilities, 500, 0, 600);
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
        player.decreaseMana(ability.getManaCost());

        if (ability.getType().equalsIgnoreCase("cleans")){
            player.increaseHealth(ability.getPower());
        }else {
            boss.decreaseHealth(ability.getPower());
        }

//        String move = promptAI();
//        executeBossMove(move);
        executeBossMove();
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

    public void executeBossMove() {
        //implement boss logic
        Random random = new Random();

        ArrayList<Ability> abilities = boss.getAbilities();
        Ability  move = abilities.get(random.nextInt(abilities.size()));
        boss.setAbilityUsed(move.getName());
        player.decreaseHealth(move.getPower());

    }

    public Map<String, Object> gameState(String userId){
//        HashMap<String, Object> state = gameStates.get(userId).getGameState();
//        Player player = (Player) state.get("player");
//        Boss boss = (Boss) state.get("boss");

//        return Map.ofEntries(
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
        return gameStates.get(userId).getGameState();
    }

    public void endGame(String userId) {
        gameStates.remove(userId);
    }
}
