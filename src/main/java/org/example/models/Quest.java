package org.example.models;
import org.example.interfaces.QuestSpec;

public class Quest implements org.example.interfaces.Quest {
    private int playerID;
    private final QuestType questType;
    private final QuestSpec questSpec;
    private final int requiredAmount;
    private int actualAmount;

    public Quest(QuestType questType,QuestSpec spec, int requiredAmount, int actualAmount) {
        this.questType = questType;
        this.questSpec = spec;
        this.requiredAmount = requiredAmount;
        this.actualAmount = 0;
    }

    public int getPlayerID() {return playerID;}
    public void setPlayerID(int playerID) {this.playerID = playerID;}
    public QuestType getQuestType() {return questType;}
    public QuestSpec getQuestSpec() {return questSpec;}
    public int getRequiredAmount() {return requiredAmount;}

    public boolean progressKill(QuestSpec questSpec) {
        if(this.questSpec != questSpec) {return false;}
        actualAmount += 1;
        return true;
    }

    public boolean progressCollect(QuestSpec questSpec, int amount) {
        if(this.questSpec != questSpec) {return false;}
        actualAmount += amount;
        return true;
    }

    boolean isCompleted(){
        if(actualAmount >= requiredAmount){System.out.println("Quest is completed");}

        return actualAmount >= requiredAmount;
    }

    @Override
    public String toString(){
        return("user "+ playerID + " quest " + questType.toString() + " " + questSpec.toString() + " required: " + requiredAmount + " actual: " + actualAmount);
    }
}
