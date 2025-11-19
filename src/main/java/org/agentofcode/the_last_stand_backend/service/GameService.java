package org.agentofcode.the_last_stand_backend.service;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.agentofcode.the_last_stand_backend.model.*;
import org.agentofcode.the_last_stand_backend.model.Character;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


@Service
public class GameService {

    private static Player player;
    private static Boss boss;
    private static Dotenv dotenv = Dotenv.load();
    private static String AI_API_KEY = dotenv.get("AI_API_KEY");
    private static String AI_API_BASE_URL = dotenv.get("AI_API_URL");


    public static void main(String[] args) {
//        boolean payersTurn = true;

        boss = new Boss("Demon General", (ArrayList<String>) Character.Skills, 250, 0, 100);


    }

    public static String promptAI() {
        ArrayList<String> bossSkills = new ArrayList<>();
        Set<String>  testing = Character.Skills.keySet();
        bossSkills.addAll(testing);

        boss = new Boss("Demon General", bossSkills, 250, 0, 100);

        OkHttpClient client = new OkHttpClient();

        String prompt = "You are the boss in this situation, choose a skill to play based on the given information";
        String context = "This is a turn based game. player status is " + player.getCharacterInfo() +
                " and the boss status is " + boss.getCharacterInfo()  + "Respond only with the skill name.";

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
            System.out.println("choice is :" + bossAttack);

            System.out.println( "the dmg :" + Character.Skills.get(bossAttack));
//            player.decreaseHealth((Integer) Character.Skills.get(bossAttack));
            System.out.println("Player: "+ player.getHealth());
            return bossAttack;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void insertPlayer( Character newPlayer){
        player = (Player) newPlayer;
    }

    public String gameIntro(){
        return "";
    }

    public Boss creatBoss(Player player){
        return new Boss();
    }

    public static void executeSkill(BaseCharacter target, String skill){
        HashMap<String, Object> skillInfo = (HashMap<String, Object>) Character.Skills.get(skill);
        String type = (String) skillInfo.get("Type");
        if (type.equals("Attack")) {
            boss.decreaseHealth((Integer) skillInfo.get("Value"));
            player.decreaseMagicPower(10);
        }
        else if (type.equals("Sustain")) {

        } else if (type.equals("Area of Attack")) {

        } else if (type.equals("Drain")) {

        } else if (type.equals("Shield")) {

        } else if (type.equals("Reflect")) {

        }else { // for Ultimates

        }

        String bossAttack = promptAI();

        executeBossMove(bossAttack);
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


    public static void executeBossMove(String move) {
        //implement boss logic

    }

    public HashMap<String, Object> gameState(){
        return player.getCharacterInfo();
    }

}
