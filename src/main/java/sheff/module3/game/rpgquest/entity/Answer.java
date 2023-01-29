package sheff.module3.game.rpgquest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Answer {
    @Getter
    private int id;
    @Getter
    private String text;
    @Getter
    private Question prev;
    @Getter
    private Question next;
}
