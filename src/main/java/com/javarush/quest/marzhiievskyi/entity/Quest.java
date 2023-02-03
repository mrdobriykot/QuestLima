package com.javarush.quest.marzhiievskyi.entity;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quest implements AbstractEntity{
    private Long id;
    private String name;
    private String startingText;
    private Long startQuestionId;
    private Collection<Question> questions = new ArrayList<>();
    private Collection<User> players = new ArrayList<>();

}
