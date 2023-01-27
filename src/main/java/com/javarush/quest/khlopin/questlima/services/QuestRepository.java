package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.Repository;
import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import com.javarush.quest.khlopin.questlima.entity.game.Question;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.utills.DB;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class QuestRepository implements Repository<Quest> {

    private final HashMap<Long,Quest> questMap = new HashMap<>();
    private static final AtomicLong id = new AtomicLong(0);


    public QuestRepository() {
        create(DB.userDataBase.get(1),"НЛО", DB.questionDataBase.getAll(), DB.questionDataBase.getAll().size());

    }


        public void create(User author, String info, List<Question> list,int countOfStages) {
        long questId = id.incrementAndGet();
        questMap.put(questId, new Quest(questId, author,info,list,countOfStages));
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
        questMap.put(idOfOlderEntity,entity);
    }

    @Override
    public Collection<Quest> getAll() {
        return questMap.values();
    }

    @Override
    public Optional<Quest> find(String id) { //TODO Вынести из абстракции???
        return Optional.ofNullable(questMap.get(Long.parseLong(id)));
    }
}
