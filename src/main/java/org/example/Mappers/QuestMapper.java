package org.example.Mappers;

import org.example.models.Quest;
import org.example.models.QuestDto;

public class QuestMapper {
    public Quest toQuest(QuestDto questDto){
        Quest quest = new Quest(
                questDto.getQuestType(),
                questDto.getSpec(),
                questDto.getRequiredAmount(),
                0);
        return quest;
    };
    public QuestDto toQuestDto(Quest quest){
        QuestDto questDto = new QuestDto(
                quest.getQuestType(),
                quest.getQuestSpec().toString(),
                quest.getRequiredAmount()
        );
        return questDto;
    }
}
