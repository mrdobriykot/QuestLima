package com.javarush.dobrov.service;

import com.javarush.dobrov.entity.Answer;
import com.javarush.dobrov.entity.Quest;
import com.javarush.dobrov.entity.Question;
import com.javarush.dobrov.repository.AnswerRepository;
import com.javarush.dobrov.repository.QuestRepository;
import com.javarush.dobrov.repository.QuestionRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestService {

    private final QuestRepository questRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private Question question;
    private StringBuilder builder;
    public Map<Integer,Integer> answersConnecting = new HashMap<>();
    private Quest quest;


    public QuestService(QuestRepository questRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questRepository = questRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Map<Long, Question> getQuestions(){
        return quest.questions;
    }

    public Map<Long, Answer> getAnswers(){
        return question.answers;
    }


    public void createQuest(String questHeader, String questText) {
        quest = new Quest(questHeader, questText);
        questRepository.create(quest);
        quest.setHeader(questHeader);

        //find questions
        Matcher questionMatcher = Pattern.compile("\\d*:.*").matcher(questText);
        while (questionMatcher.find()) {
            String question_fullText = questionMatcher.group();
            String question_number = question_fullText.substring(0, question_fullText.indexOf(":")).strip();
            String questionText = question_fullText.substring(question_fullText.indexOf(":") + 1).strip();

            question = new Question(questionText);
            questionRepository.create(question);
            quest.questions.put(Long.valueOf(question_number), question);


        }

//        for (Map.Entry<Long, Question> longQuestionEntry : quest.questions.entrySet()) {
//            System.out.println(longQuestionEntry);
//        }

        //find answers
        Matcher answersMatcher = Pattern.compile("\\d*<.*").matcher(questText);
        while (answersMatcher.find()) {
            String answer_fullText = answersMatcher.group();
            String answer_number = answer_fullText.substring(0, answer_fullText.indexOf("<")).strip();
            String answerText = answer_fullText.substring(answer_fullText.indexOf("<") + 1).strip();

            Answer answer = new Answer(answerText);
            answer.setAnswer_number(Long.valueOf(answer_number));
            answerRepository.create(answer);
            question.answers.put(answer.getId(), answer);

        }

//        for (Map.Entry<Long, Answer> longAnswerEntry : question.answers.entrySet()) {
//            System.out.println(longAnswerEntry.getValue());
//        }


        //finding out how many answers in each question
        builder = new StringBuilder();
        Matcher matcher = Pattern.compile("\\d*[<:].*").matcher(questText);
        while (matcher.find()) {
            //System.out.println(matcher.group());
            builder.append(matcher.group());
        }
        String substring = "";
        for (int i = 1; i <= quest.questions.size(); i++) {
            if (i == 1) {
                substring = builder.substring(builder.indexOf(String.valueOf(i)), builder.lastIndexOf(String.valueOf(i + 1)));
//                System.out.println(substring);
                String[] split = substring.split("<");
                answersConnecting.put(i,split.length - 1);
//                System.out.println(split.length - 1);
            } else if (i < quest.questions.size() + 1) {
                if (i == quest.questions.size()) {
                    substring = builder.substring(builder.lastIndexOf(String.valueOf(i)));
//                    System.out.println(substring);
                    String[] split = substring.split("<");
                    answersConnecting.put(i,split.length - 1);
//                    System.out.println(split.length - 1);
                    break;
                }
                substring = builder.substring(builder.lastIndexOf(String.valueOf(i)), builder.lastIndexOf(String.valueOf(i + 1)));
//                System.out.println(substring);
                String[] split = substring.split("<");
                answersConnecting.put(i,split.length - 1);
//                System.out.println(split.length - 1);
            }
        }


    }


}
