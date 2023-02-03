package com.javarush.quest.sternard.service;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.util.Parameter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static com.javarush.quest.sternard.listener.Listener.questRepository;
import static com.javarush.quest.sternard.util.Parameter.RATING_UP;

public enum QuestService implements Service<Quest> {
    INSTANCE;

    @Override
    public Optional<Quest> create(Map<String, String> checkedParamsMap) {
        return Optional.empty();
    }

    @Override
    public boolean update(Quest quest, Map<String, String> checkedParamsMap) {
        String title = checkedParamsMap.get(Parameter.TITLE);
        String description = checkedParamsMap.get(Parameter.DESCRIPTION);
        String creationDate = checkedParamsMap.get(Parameter.CREATION_DATE);
        int views = Integer.parseInt(checkedParamsMap.get(Parameter.VIEWS));
        String author = checkedParamsMap.get(Parameter.AUTHOR);
        double rating = Double.parseDouble(checkedParamsMap.get(Parameter.RATING));
        String image = checkedParamsMap.get(Parameter.IMAGE);

        for (Quest qst : questRepository.getAllEntities()) {
            if (qst.getId() == quest.getId()) {
                qst.setTitle(title);
                qst.setDescription(description);
                qst.setCreationDate(LocalDate.parse(creationDate));
                qst.setViews(views);
                qst.setAuthor(author);
                qst.setRating(rating);
                qst.setImage(image);
                questRepository.update(qst);
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(long id) {
        questRepository.delete(id);
    }

    @Override
    public Map<Long, Quest> getEntitiesMap() {
        return questRepository.getEntitiesMap();
    }

    @Override
    public Collection<Quest> getAllEntities() {
        return questRepository.getAllEntities();
    }

    public Double updateRating(Quest quest, String upDown) {
        double upDownUnitQuestRating = Settings.get().getUpDownUnitQuestRating();
        double minQuestRating = Settings.get().getMinQuestRating();
        double maxQuestRating = Settings.get().getMaxQuestRating();

        double getRating = quest.getRating();
        double increaseRating = getRating + upDownUnitQuestRating;
        double reduceRating = getRating - upDownUnitQuestRating;
        double rating = (upDown.equals(RATING_UP) ? increaseRating : reduceRating);
        if (rating < minQuestRating) rating = minQuestRating;
        if (rating > maxQuestRating) rating = maxQuestRating;
        quest.setRating(rating);
        questRepository.update(quest);
        return rating;
    }

    public void updateViews(Quest quest) {
        int views = quest.getViews() + 1;
        quest.setViews(views);
        questRepository.update(quest);
    }

}