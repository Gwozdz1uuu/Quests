package org.example.services;

import org.example.models.QuestDto;
import org.example.repositories.QuestDataBase;


import java.util.List;

public class QuestService {
    private final QuestDataBase questDataBase;

    public QuestService(QuestDataBase questDataBase) {
        this.questDataBase = questDataBase;
    }

    public List<QuestDto> listAvailableQuests() {
        return questDataBase.getQuests();
    }

    public QuestDto getQuestByIdFromList(int index) {
        return questDataBase.getQuests().get(index);
    }
    public void removeQuestFromDB(QuestDto q) {
        questDataBase.removeQuest(q);
    }
    public void addQuestToDB(QuestDto q) {
        questDataBase.addQuest(q);
    }
}
