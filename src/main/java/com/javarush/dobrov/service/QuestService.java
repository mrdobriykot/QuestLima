package com.javarush.dobrov.service;

import com.javarush.dobrov.entity.Answer;
import com.javarush.dobrov.entity.Quest;
import com.javarush.dobrov.entity.Question;
import com.javarush.dobrov.repository.AnswerRepository;
import com.javarush.dobrov.repository.QuestRepository;
import com.javarush.dobrov.repository.QuestionRepository;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestService {

    private final QuestRepository questRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestService(QuestRepository questRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questRepository = questRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public void createQuest(String questHeader, String questText) {
        Quest quest = new Quest(questHeader, questText);
        questRepository.create(quest);
        quest.setHeader(questHeader);


        Matcher questionMatcher = Pattern.compile("\\d*:[\\s*[а-яА-Я]?]*").matcher(questText);
        while (questionMatcher.find()) {
            String question_fullText = questionMatcher.group();
            String question_number = question_fullText.substring(0, question_fullText.indexOf(":")).strip();
            String questionText = question_fullText.substring(question_fullText.indexOf(":") + 1).strip();

            Question question = new Question(questionText, quest.getId());
            questionRepository.create(question);
            quest.questions.put(Long.valueOf(question_number), question);


        }
        Matcher answersMatcher = Pattern.compile("\\d*<[\\s*[а-яА-Я]?]*").matcher(questText);
        while (answersMatcher.find()){
            String answer_fullText = questionMatcher.group();
            String id = answer_fullText.substring(0, answer_fullText.indexOf("<")).strip();
            String answerText = answer_fullText.substring(answer_fullText.indexOf("<") + 1).strip();

         //   Answer answer = new Answer()
        }


    }


}
