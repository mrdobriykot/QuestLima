package com.javarush.khmelov.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class QuestionDto {
    Long id;
    Long questId;
    String image;
    String text;
    Collection<AnswerDto> answers;
}
