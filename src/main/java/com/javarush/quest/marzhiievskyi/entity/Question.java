package com.javarush.quest.marzhiievskyi.entity;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question implements AbstractEntity{
    private Long id;
    private String text;
    private Long questId;
    private GameState gameState;
    private Collection<Answer> answerList = new ArrayList<>();

}
