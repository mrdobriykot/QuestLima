package com.javarush.quest.marzhiievskyi.service;

import com.javarush.quest.marzhiievskyi.entity.*;
import com.javarush.quest.marzhiievskyi.repository.GameSessionRepository;
import com.javarush.quest.marzhiievskyi.repository.QuestRepository;
import com.javarush.quest.marzhiievskyi.repository.Repository;
import com.javarush.quest.marzhiievskyi.util.quests.test.DemoQuest;
import com.javarush.quest.marzhiievskyi.util.quests.test.TestQuest;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public enum GameSessionService {
    GAME_SESSION_SERVICE;

    private final Repository<GameSession> gameSessionRepository = new GameSessionRepository();
    private final Repository<Quest> questRepository = new QuestRepository();
    private final UserService userService = UserService.USER_SERVICE;



    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public Quest getQuest(Long questId) {
        return questRepository.get(questId);
    }

    public Collection<Quest> getAllQuests() {
        return questRepository.getAll();
    }

    public void createQuest() {
        TestQuest quest = new TestQuest();
        DemoQuest demoQuest = new DemoQuest();
        questRepository.create(quest);
        questRepository.create(demoQuest);
    }

    public Optional<Question> getQuestion(Long questId, Long questionId) {
        Quest quest = questRepository.get(questId);
        Collection<Question> questions = quest.getQuestions();
        return questions.stream().filter(q -> Objects.equals(q.getId(), questionId)).findAny();
    }

    public Collection<Answer> getAnswers(Long questId, Long questionId) {
        Optional<Question> question = getQuestion(questId, questionId);
        return question.map(Question::getAnswerList).orElse(null);
    }

}
