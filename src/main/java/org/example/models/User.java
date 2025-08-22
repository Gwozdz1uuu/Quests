package org.example.models;

public class User {
    private int id;
    private String username;
    private Quest quest;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public Quest getQuest() {
        return quest;
    }


    public void kill(MobSpec mob){
        if(!hasActiveQuest()) return;
        quest.progressKill(mob);
        if(quest.isCompleted()) this.quest = null;
    }
    public void collect(ItemSpec item, int amount){
        if(!hasActiveQuest()) return;
        quest.progressCollect(item, amount);
        if(quest.isCompleted()) this.quest = null;
    }

    public boolean hasActiveQuest() {
        return quest != null;
    }

    public void assignQuest(Quest quest) {
        this.quest = quest;
        quest.setPlayerID(this.id);
    }

    public void dropQuest() {
        this.quest = null;
    }
}
