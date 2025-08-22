package org.example;


import org.example.controllers.GameController;
import org.example.repositories.QuestDataBase;
import org.example.repositories.UserDataBase;
import org.example.services.QuestService;
import org.example.services.UserQuestService;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception{


        QuestDataBase questDataBase = new QuestDataBase();
        try{
            questDataBase.loadQuestsFromJSON();

        } catch(IOException e){
            System.out.println("Failed to load quests.json");
            e.printStackTrace();
        }
        QuestService questService = new QuestService(questDataBase);
        UserQuestService userQuestService = new UserQuestService(questService);
        UserDataBase userDataBase = new UserDataBase();

        new GameController(userQuestService, questService, userDataBase).gameLoop();
    }
}