package org.example.controllers;
import java.util.*;

import org.example.models.ItemSpec;
import org.example.models.MobSpec;
import org.example.models.User;
import org.example.repositories.UserDataBase;
import org.example.services.QuestService;
import org.example.services.UserQuestService;

import java.util.Scanner;



public class GameController {
    private final Scanner in = new Scanner(System.in);

    private final UserQuestService userQuestService;
    private final QuestService questService;
    private final UserDataBase userDataBase;
    private User player;

    public GameController(
                          UserQuestService userQuestService,
                          QuestService questService,
                          UserDataBase userDataBase
                         ) {

        this.userQuestService = userQuestService;
        this.questService = questService;
        this.userDataBase = userDataBase;
    }

    public void gameLoop() {
        boolean loop = true;
        player = choosePlayer(true);

        while (loop) {
            printHeader();
            System.out.println("1) List available quests");
            System.out.println("2) Assign quest");
            System.out.println("3) Show active quest");
            System.out.println("4) Kill mob");
            System.out.println("5) Collect item");
            System.out.println("6) Drop your active quest");
            System.out.println("7) Quit");
            System.out.println("8) Switch player");
            System.out.println("Enter choice: ");
            int choice = readInt("Choose: ", 1,8);

            switch (choice) {
                case 1: listAvailableQuests(); break;
                case 2: assignQuest(); break;
                case 3: showActiveQuest(); break;
                case 4: kill(); break;
                case 5: collect(); break;
                case 6: cancelQuest(); break;
                case 7: {
                    System.out.println("Bye!");
                    loop = false;
                    break;
                }
                case 8: {
                    player = choosePlayer(true); break;
                }
                default:
                    System.out.println("Invalid choice");
            }
            if(loop){
                waitEnter("Press enter to continue");
            }


        }
    }
    private User choosePlayer(boolean allow) {
        while (true) {
            var users = userDataBase.getUsers();

            System.out.println("Choose player:");
            for (int i = 0; i < users.size(); i++) {
                System.out.printf("[%d] %s%n", i, users.get(i).getUsername());
            }

            int index = readInt("Index: ", 0, users.size() - 1);
            return users.get(index);
        }
    }

    public void listAvailableQuests() {
         var quests = questService.listAvailableQuests();
         if(quests.isEmpty()) {
             System.out.println("There are no quests available");
             return;
         }
        System.out.println("Available quests:");
         for (int i=0; i<quests.size(); i++) {
             System.out.println("[%d] %s".formatted(i, quests.get(i)));
         }
    }
    public void assignQuest() {
        var list = questService.listAvailableQuests();
        if(list.isEmpty()) {
            System.out.println("There are no quests available");
            return;
        }
        listAvailableQuests();
        System.out.println("Pick quest index: ");
        int index = readInt("Pick quest index: ", 0,list.size() -1);
        userQuestService.assignQuest(player, index);
        System.out.println("Quest assigned: " + player.getQuest().toString());
    }

    public void showActiveQuest() {
        if(!player.hasActiveQuest()){
            System.out.println("There is no active quest");
            return;
        }
        System.out.println("Quest active: " + player.getQuest().toString());
    }

    public void cancelQuest() {
        if(!player.hasActiveQuest()){
            System.out.println("There is no active quest");
            return;
        }
        System.out.println("Quest canceled: " + player.getQuest().toString());
        userQuestService.dropQuest(player);
    }

    public void kill(){
        MobSpec mob = chooseEnum("Which mob",  MobSpec.class);
        player.kill(mob);
        System.out.println("Killed: "+ mob);
    }

    private void collect(){
        ItemSpec item = chooseEnum("Which item",  ItemSpec.class);
        int amount = readInt("How many items: ",1,1000000);
        player.collect(item,amount);
        System.out.println("Collected: " + item + ", amount: " + amount);
    }

    private void waitEnter(String prompt){
        System.out.println(prompt);
        in.nextLine();
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine();
            try {
                int v = Integer.parseInt(s.trim());
                if (v < min || v > max) throw new NumberFormatException();
                return v;
            } catch (NumberFormatException e) {
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            }
        }
    }

    private <E extends Enum<E>> E chooseEnum(String prompt, Class<E> enumClass) {
        E[] values =  enumClass.getEnumConstants();
        System.out.println(prompt + ":");
        for (int i = 0; i < values.length; i++) {
            System.out.println(i + ")" + values[i].name());
        }
        int index = readInt("index: ", 0,values.length-1);
        return values[index];
    }

    public void printHeader() {
        System.out.println("\n==============================");
        System.out.println(" Player: %s".formatted(player.getUsername()));
        System.out.println(" Quest : %s".formatted(
                player.hasActiveQuest() ? player.getQuest().toString() : "(none)"));
                System.out.println("==============================");
    }
}
