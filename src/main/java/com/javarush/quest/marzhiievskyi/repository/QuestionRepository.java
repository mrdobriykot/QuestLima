package com.javarush.quest.marzhiievskyi.repository;

import com.javarush.quest.marzhiievskyi.entity.GameState;
import com.javarush.quest.marzhiievskyi.entity.Question;
import com.javarush.quest.marzhiievskyi.resource.TextForQuest;

import java.util.stream.Stream;

public class QuestionRepository extends BaseRepository<Question> {
    public QuestionRepository() {
        Question startingQuestion = Question.builder()
                .id(-1L)
                .text(TextForQuest.STARTING_QUESTION_TEXT)
                .gameState(GameState.PLAY)
                .build();
        create(startingQuestion);//TODO initialize where should be?
    }

    @Override
    public Stream<Question> find(Question pattern) {
        return map.values().stream()
                .filter(question -> nullOrEquals(pattern.getId(), question.getId()))
                .filter(question -> nullOrEquals(pattern.getText(), question.getText()))
                .filter(question -> nullOrEquals(pattern.getQuestId(), question.getQuestId()))
                .filter(question -> nullOrEquals(pattern.getGameState(), question.getGameState()));
    }
}
