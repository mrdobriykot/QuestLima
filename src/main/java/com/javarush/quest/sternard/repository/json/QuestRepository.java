package com.javarush.quest.sternard.repository.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.javarush.quest.sternard.repository.Repository;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class QuestRepository extends BaseRepository<Quest> implements Repository<Quest> {

    public QuestRepository() {
        this.getEntitiesMap();
    }

    @Override
    public void create(Quest entity) {

    }

    @Override
    public void update(Quest entity) {  // replace node need, but how...
        try {
            String stringId = String.valueOf(entity.getId());
            JsonNode rootTree = readJsonTreeValue(PATH_QUESTS_JSON_DB);
            JsonNode value = rootTree.path(stringId); // analog rootTree.get(stringId)
            ((ObjectNode) value).put("title", entity.getTitle());
            ((ObjectNode) value).put("description", entity.getDescription());
            ((ObjectNode) value).put("creationDate", entity.getCreationDate().toString());
            ((ObjectNode) value).put("views", entity.getViews());
            ((ObjectNode) value).put("author", entity.getAuthor());
            ((ObjectNode) value).put("rating", entity.getRating());
            ((ObjectNode) value).put("image", entity.getImage());
            writeJsonTreeValue(rootTree, PATH_QUESTS_JSON_DB);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try {
            JsonNode rootTree = readJsonTreeValue(PATH_QUESTS_JSON_DB);
            ObjectNode objNode = (ObjectNode) rootTree;
            objNode.remove(String.valueOf(id));
            writeJsonTreeValue(rootTree, PATH_QUESTS_JSON_DB);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public Map<Long, Quest> getEntitiesMap() {
        return readMapFromJson(PATH_QUESTS_JSON_DB, Quest.class);
    }

}
