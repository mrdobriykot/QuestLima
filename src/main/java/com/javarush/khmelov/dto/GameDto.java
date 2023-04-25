package com.javarush.khmelov.dto;

import com.javarush.khmelov.entity.GameState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameDto {
    Long id;
    Long questId;
    Long userId;
    QuestionDto question;
    GameState gameState;
}
