package org.example.services;

import org.example.Mappers.QuestMapper;
import org.example.models.Quest;
import org.example.models.QuestDto;
import org.example.models.User;

public class UserQuestService {
    private final QuestService questService;
    private final QuestMapper questMapper = new QuestMapper();

    public UserQuestService(QuestService questService) {
        this.questService = questService;
    }

    public void assignQuest(User user, int questIndex) {
        if(user.hasActiveQuest()){
            return;
        }

        QuestDto questDto = questService.getQuestByIdFromList(questIndex);
        Quest quest = questMapper.toQuest(questDto);
        questService.removeQuestFromDB(questDto);

        user.assignQuest(quest);
    }

    public void dropQuest(User user) {
        if(user.hasActiveQuest()){
            Quest quest = user.getQuest();
            QuestDto questDto = questMapper.toQuestDto(quest);

            questService.addQuestToDB(questDto);
            user.dropQuest();
        }
    }




}
