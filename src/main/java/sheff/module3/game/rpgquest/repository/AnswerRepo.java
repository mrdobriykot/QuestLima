package sheff.module3.game.rpgquest.repository;

import sheff.module3.game.rpgquest.entity.Answer;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerRepo extends Repository<Integer, Answer> {

    public List<Answer> getAnswersByQuestionId(int questionId) {
        return repo.values().stream()
                .filter(v -> {
                    if (v.getPrev() == null) {
                        throw new IllegalArgumentException();
                    }
                    return v.getPrev().getId() == questionId;
                })
                .collect(Collectors.toList());
    }
}
