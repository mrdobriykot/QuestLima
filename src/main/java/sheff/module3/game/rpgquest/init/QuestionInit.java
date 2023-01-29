package sheff.module3.game.rpgquest.init;

import sheff.module3.game.rpgquest.entity.Question;
import sheff.module3.game.rpgquest.repository.LocationRepo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class QuestionInit {
    @Getter
    private final List<Question> questions = new ArrayList<>();

    public QuestionInit(LocationRepo locationRepo) {

        questions.add(new Question(1, "Приветствую тебя!", Question.Type.CONTINUE));
        questions.add(new Question(2, "Удачи!", Question.Type.END));
        questions.add(new Question(3, "Куда направляешься?", Question.Type.CONTINUE));
        questions.add(new Question(4, "Ясно! Чем могу помочь?", Question.Type.CONTINUE));
        questions.add(new Question(5, "Помогу решить вопрос с " + locationRepo.getById(2).getName(), Question.Type.CONTINUE));
        questions.add(new Question(6, "Да без проблем, готово!", Question.Type.SUCCESS));
        questions.add(new Question(7, "Привет!", Question.Type.CONTINUE));
        questions.add(new Question(8, "На связи!", Question.Type.END));
        questions.add(new Question(9, "Куда спешишь?", Question.Type.CONTINUE));
        questions.add(new Question(10, "Не близко! Помощь нужна?", Question.Type.CONTINUE));
        questions.add(new Question(11, "Разрулю вопрос с " + locationRepo.getById(5).getName(), Question.Type.CONTINUE));
        questions.add(new Question(12, "Не вопрос, сделано!", Question.Type.SUCCESS));
        questions.add(new Question(13, "Приветствую!", Question.Type.CONTINUE));
        questions.add(new Question(14, "Счастливо!", Question.Type.END));
        questions.add(new Question(15, "Куда путь держишь?", Question.Type.CONTINUE));
        questions.add(new Question(16, "Знаю такой, да. Помочь?", Question.Type.CONTINUE));
        questions.add(new Question(17, "Договорюсь по " + locationRepo.getById(6).getName(), Question.Type.CONTINUE));
        questions.add(new Question(18, "Все схвачено, вперед!", Question.Type.SUCCESS));
        questions.add(new Question(19, "Приветствую тебя!", Question.Type.CONTINUE));
        questions.add(new Question(20, "Удачи!", Question.Type.END));
        questions.add(new Question(21, "Куда направляешься?", Question.Type.CONTINUE));
        questions.add(new Question(22, "Ясно! Чем могу помочь?", Question.Type.CONTINUE));
        questions.add(new Question(23, "Помогу решить вопрос с " + locationRepo.getById(10).getName(), Question.Type.CONTINUE));
        questions.add(new Question(24, "Да без проблем, готово!", Question.Type.SUCCESS));
        questions.add(new Question(25, "Приветствую тебя!", Question.Type.CONTINUE));
        questions.add(new Question(26, "Удачи!", Question.Type.END));
        questions.add(new Question(27, "Куда направляешься?", Question.Type.CONTINUE));
        questions.add(new Question(28, "Ясно! Чем могу помочь?", Question.Type.CONTINUE));
        questions.add(new Question(29, "Помогу решить вопрос с " + locationRepo.getById(8).getName(), Question.Type.CONTINUE));
        questions.add(new Question(30, "Да без проблем, готово!", Question.Type.SUCCESS));
        questions.add(new Question(31, "Приветствую тебя!", Question.Type.CONTINUE));
        questions.add(new Question(32, "Удачи!", Question.Type.END));
        questions.add(new Question(33, "Куда направляешься?", Question.Type.CONTINUE));
        questions.add(new Question(34, "Ясно! Чем могу помочь?", Question.Type.CONTINUE));
        questions.add(new Question(35, "Помогу решить вопрос с " + locationRepo.getById(12).getName(), Question.Type.CONTINUE));
        questions.add(new Question(36, "Да без проблем, готово!", Question.Type.SUCCESS));
        questions.add(new Question(37, "Приветствую тебя!", Question.Type.CONTINUE));
        questions.add(new Question(38, "Удачи!", Question.Type.END));
        questions.add(new Question(39, "Куда направляешься?", Question.Type.CONTINUE));
        questions.add(new Question(40, "Ясно! Чем могу помочь?", Question.Type.CONTINUE));
        questions.add(new Question(41, "Помогу решить вопрос с " + locationRepo.getById(13).getName(), Question.Type.CONTINUE));
        questions.add(new Question(42, "Да без проблем, готово!", Question.Type.SUCCESS));
        questions.add(new Question(43, "Приветствую тебя!", Question.Type.CONTINUE));
        questions.add(new Question(44, "Удачи!", Question.Type.END));
        questions.add(new Question(45, "Куда направляешься?", Question.Type.CONTINUE));
        questions.add(new Question(46, "Ясно! Чем могу помочь?", Question.Type.CONTINUE));
        questions.add(new Question(47, "Помогу решить вопрос с " + locationRepo.getById(15).getName(), Question.Type.CONTINUE));
        questions.add(new Question(48, "Да без проблем, готово!", Question.Type.SUCCESS));
        questions.add(new Question(49, "Приветствую тебя!", Question.Type.CONTINUE));
        questions.add(new Question(50, "Удачи!", Question.Type.END));
        questions.add(new Question(51, "Куда направляешься?", Question.Type.CONTINUE));
        questions.add(new Question(52, "Ясно! Чем могу помочь?", Question.Type.CONTINUE));
        questions.add(new Question(53, "Помогу решить вопрос с " + locationRepo.getById(14).getName(), Question.Type.CONTINUE));
        questions.add(new Question(54, "Да без проблем, готово!", Question.Type.SUCCESS));
    }
}
