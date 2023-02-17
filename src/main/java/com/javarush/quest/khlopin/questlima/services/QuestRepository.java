package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import com.javarush.quest.khlopin.questlima.entity.game.Question;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.utills.DB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class QuestRepository implements Repository<Quest> {

    private final HashMap<Long, Quest> questMap = new HashMap<>();
    private static final AtomicLong id = new AtomicLong(0);
    private static final Logger log = LogManager.getLogger(QuestRepository.class);


    public QuestRepository() {
        UploadQuestFromJson uploadQuestFromJson = new UploadQuestFromJson(new String[]{"/quests/quest1.json", "/quests/quest2.json"});
        for (Quest quest : uploadQuestFromJson.readJsonsAndCreateQuests()) {
            questMap.put(id.incrementAndGet(), quest);
        }
        log.trace("Репозиторий с квестами был загружен");
    }


    public void create(User author, String info, List<Question> list, int countOfStages) {
        long questId = id.incrementAndGet();
        questMap.put(questId, new Quest(questId, author, info, list, countOfStages));
    }

    @Override
    public Quest get(long id) {
        return questMap.get(id);
    }

    @Override
    public void delete(long id) {
        questMap.remove(id);
    }

    @Override
    public void update(long idOfOlderEntity, Quest entity) {
        delete(idOfOlderEntity);
        questMap.put(idOfOlderEntity, entity);
    }

    @Override
    public Collection<Quest> getAll() {
        return questMap.values();
    }


    public List<Question> findAllQustionsForQuest(long questId) {
        List<Question> all = DB.questionDataBase.getAll();
        List<Question> questionList = new ArrayList<>();
        for (Question question : all) {
            if (question.getQuestId() == questId) {
                questionList.add(question);
            }
        }
        return questionList;
    }
}
