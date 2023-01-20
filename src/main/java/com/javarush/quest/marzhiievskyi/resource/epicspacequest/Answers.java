package com.javarush.quest.marzhiievskyi.resource.epicspacequest;

import com.javarush.quest.marzhiievskyi.entity.Answer;
import com.javarush.quest.marzhiievskyi.resource.TextForQuest;
import com.javarush.quest.marzhiievskyi.service.AnswerService;

public class Answers {
    AnswerService answerService = AnswerService.ANSWER_SERVICE;

    public Answers() {

        Answer agreeFirstQuestionAnswer = Answer.builder()
                .id(0L)
                .text(TextForQuest.AGREE_ANSWER)
                .build();

        Answer disagreeFirstQuestionAnswer = Answer.builder()
                .id(0L)
                .text(TextForQuest.DISAGREE_ANSWER)
                .build();

        answerService.create(agreeFirstQuestionAnswer);
        answerService.create(disagreeFirstQuestionAnswer);


    }
}
