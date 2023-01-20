package com.javarush.quest.marzhiievskyi.resource.epicspacequest;

import com.javarush.quest.marzhiievskyi.entity.GameState;
import com.javarush.quest.marzhiievskyi.entity.Question;
import com.javarush.quest.marzhiievskyi.resource.TextForQuest;
import com.javarush.quest.marzhiievskyi.service.QuestionService;

public class Questions {
    QuestionService questionService = QuestionService.QUESTION_SERVICE;

    public void init() {
        Question startingQuestion = Question.builder()
                .id(0L)
                .text(TextForQuest.STARTING_QUESTION_TEXT)
                .gameState(GameState.PLAY)
                .build();
        questionService.create(startingQuestion);
    }
}
