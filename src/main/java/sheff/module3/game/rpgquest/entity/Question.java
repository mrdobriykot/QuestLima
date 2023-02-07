package sheff.module3.game.rpgquest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Question {
    private int id;
    private String text;
    private Type type;

    public enum Type {
        CONTINUE,
        END,
        SUCCESS
    }
}
