package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.config.AppConfig;
import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.Answer;
import com.javarush.quest.uzienko.questlima.entity.Quest;
import com.javarush.quest.uzienko.questlima.entity.Question;
import com.javarush.quest.uzienko.questlima.repository.AnswerMapRepository;
import com.javarush.quest.uzienko.questlima.repository.QuestMapRepository;
import com.javarush.quest.uzienko.questlima.repository.QuestionMapRepository;
import com.javarush.quest.uzienko.questlima.utils.YmlReader;

public class InitService {
    private final AnswerMapRepository answerMapRepository = Winter.getBean(AnswerMapRepository.class);
    private final QuestionMapRepository questionMapRepository = Winter.getBean(QuestionMapRepository.class);
    private final QuestMapRepository questMapRepository = Winter.getBean(QuestMapRepository.class);

    public void initQuestData() {
        if (answerMapRepository.isEmpty()) {
            YmlReader.getData(AppConfig.ANSWERS_YML, Answer.class)
                    .forEach(answerMapRepository::update);
        }

        if (questMapRepository.isEmpty()) {
            YmlReader.getData(AppConfig.QUESTS_YML, Quest.class)
                    .forEach(questMapRepository::update);
        }

        if (questionMapRepository.isEmpty()) {
            YmlReader.getData(AppConfig.QUESTIONS_YML, Question.class)
                    .forEach(questionMapRepository::update);
        }
    }
}
