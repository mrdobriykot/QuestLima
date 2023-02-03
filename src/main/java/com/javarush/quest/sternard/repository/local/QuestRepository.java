package com.javarush.quest.sternard.repository.local;

import com.javarush.quest.sternard.entities.Answer;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.entities.QuestState;
import com.javarush.quest.sternard.entities.Question;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class QuestRepository extends BaseRepository<Quest> {
    private static final long QUEST_1_ID = 1L;
    private final Map<Long, Question> quest_1_Questions = new HashMap<>();
    private final AtomicLong answerId = new AtomicLong(0);

    public QuestRepository() {
        getQuests();
    }

    private void getQuests() {
        List<Question> questions = getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            long questionId = (long) i + 1;
            this.quest_1_Questions.put(questionId, questions.get(i));
        }
        this.map.put(QUEST_1_ID, quest_1);
    }

    // First quest with questions/answers start code
    final Quest quest_1 = Quest.builder()
            .id(QUEST_1_ID)
            .title("JavaRush квест про НЛО")
            .description("Небольшой текстовый квест. Вопрос на каждом следующем шаге зависит от предыдущего ответа. " +
                    "НЛО значит «неопознанный летающий объект». Существуют ли они в действительности? Квест по умолчанию из JavaRush задания.")
            .image("267481871276121334.jpg")
            .creationDate(LocalDate.now())
            .views(0)
            .rating(10.0)
            .author("Login1")
            .firstQuestionId(1)
            .question(quest_1_Questions)
            .build();

    // questions/answers

    private List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(Question.builder()
                .questId(QUEST_1_ID)
                .image("297101463842344977.jpg")
                .text("Ты потерял память. Принять вызов НЛО?")
                .questState(QuestState.PLAYING)
                .answers(List.of(
                        Answer.builder().id(answerId.incrementAndGet()).answer("Принять вызов").nextQuestionId(2L).build(),
                        Answer.builder().id(answerId.incrementAndGet()).answer("Отклонить вызов").nextQuestionId(4L).build()
                )).build());

        questions.add(Question.builder()
                .questId(QUEST_1_ID)
                .image("854725877326409297.jpg")
                .text("Ты принял вызов. Поднимаешься на мостик к капитану?")
                .questState(QuestState.PLAYING)
                .answers(List.of(
                        Answer.builder().id(answerId.incrementAndGet()).answer("Подняться на мостик").nextQuestionId(3L).build(),
                        Answer.builder().id(answerId.incrementAndGet()).answer("Отказаться подниматься на мостик").nextQuestionId(5L).build()
                )).build());

        questions.add(Question.builder()
                .questId(QUEST_1_ID)
                .image("435433358797987887.jpg")
                .text("Ты поднялся на мостик. Ты кто?")
                .questState(QuestState.PLAYING)
                .answers(List.of(
                        Answer.builder().id(answerId.incrementAndGet()).answer("Рассказать правду о себе").nextQuestionId(7L).build(),
                        Answer.builder().id(answerId.incrementAndGet()).answer("Солгать о себе").nextQuestionId(6L).build()
                )).build());

        questions.add(Question.builder()
                .questId(QUEST_1_ID)
                .image("979523541333069240.jpg")
                .text("Ты отклонил вызов. Поражение.")
                .questState(QuestState.LOSE)
                .build());

        questions.add(Question.builder()
                .questId(QUEST_1_ID)
                .image("979523541333069240.jpg")
                .text("Ты не пошёл на переговоры. Поражение.")
                .questState(QuestState.LOSE)
                .build());

        questions.add(Question.builder()
                .questId(QUEST_1_ID)
                .image("979523541333069240.jpg")
                .text("Твою ложь разоблачили. Поражение.")
                .questState(QuestState.LOSE)
                .build());

        questions.add(Question.builder()
                .questId(QUEST_1_ID)
                .image("258651652487124225.jpg")
                .text("Тебя вернули домой. Победа.")
                .questState(QuestState.WIN)
                .build());

        return questions;
    }
    // First quest with questions/answers end code
}