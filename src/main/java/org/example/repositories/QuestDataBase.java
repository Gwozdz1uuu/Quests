package org.example.repositories;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.QuestDto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class QuestDataBase {
    private final String jsonPath = "src/main/resources/quests.json";
    ObjectMapper objectMapper = new ObjectMapper();
    List<QuestDto> quests = new ArrayList<QuestDto>();

    public List<QuestDto> getQuests() {
        return quests;
    }

    public void loadQuestsFromJSON() throws IOException {
        quests = objectMapper.readValue(
                new File(jsonPath),
                new TypeReference<List<QuestDto>>() {}
        );
    }

    public void removeQuest(QuestDto questDto) {
        quests.remove(questDto);
    }
    public void addQuest(QuestDto questDto) {
        quests.add(questDto);
    }

}
