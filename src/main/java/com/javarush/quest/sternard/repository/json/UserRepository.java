package com.javarush.quest.sternard.repository.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.javarush.quest.sternard.repository.Repository;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class UserRepository extends BaseRepository<User> implements Repository<User> {
    public UserRepository() {
        this.getEntitiesMap();
    }

    @Override
    public void create(User entity) {
        long entityId = id.incrementAndGet();
        entity.setId(entityId);
        try {
            ObjectNode objNode = (ObjectNode) readJsonTreeValue(PATH_USERS_JSON_DB);
            JsonNode jsonNode = OBJECT_MAPPER.valueToTree(entity);
            objNode.putIfAbsent(String.valueOf(entityId), jsonNode);
            writeJsonTreeValue(objNode, PATH_USERS_JSON_DB);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try {
            String stringId = String.valueOf(entity.getId());
            JsonNode rootTree = readJsonTreeValue(PATH_USERS_JSON_DB);
            JsonNode value = rootTree.path(stringId);
            ((ObjectNode) value).put("login", entity.getLogin());
            ((ObjectNode) value).put("password", entity.getPassword());
            ((ObjectNode) value).put("profileImage", entity.getProfileImage());
            ((ObjectNode) value).put("playedQuestsCount", entity.getPlayedQuestsCount());
            ((ObjectNode) value).put("role", entity.getRole().toString());
            writeJsonTreeValue(rootTree, PATH_USERS_JSON_DB);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try {
            JsonNode rootTree = readJsonTreeValue(PATH_USERS_JSON_DB);
            ObjectNode objNode = (ObjectNode) rootTree;
            objNode.remove(String.valueOf(id));
            writeJsonTreeValue(rootTree, PATH_USERS_JSON_DB);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public Map<Long, User> getEntitiesMap() {
        return readMapFromJson(PATH_USERS_JSON_DB, User.class);
    }
}