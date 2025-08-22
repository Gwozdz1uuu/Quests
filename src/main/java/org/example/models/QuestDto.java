package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.interfaces.QuestSpec;

public class QuestDto {
    @JsonProperty("questType")
    private QuestType questType;
    @JsonProperty
    private String spec;
    @JsonProperty
    private int requiredAmount;

    public QuestDto(QuestType questType, String spec, int requiredAmount) {
        this.questType = questType;
        this.spec = spec;
        this.requiredAmount = requiredAmount;
    }
    public QuestDto(){};

    public QuestSpec getSpec() {
        return switch (questType) {
            case KILL -> MobSpec.valueOf(spec.toUpperCase());
            case COLLECT -> ItemSpec.valueOf(spec.toUpperCase());
        };
    }
    public QuestType getQuestType() {
        return questType;
    }
    public int  getRequiredAmount() {
        return requiredAmount;
    }

    @Override
    public String toString() {
        return "Quest: " + questType + " " + requiredAmount + " " + spec;
    }
}
