package com.javarush.quest.sternard.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Question {
    Long questId;
    String image;
    String text;
    QuestState questState;
    List<Answer> answers;
}
