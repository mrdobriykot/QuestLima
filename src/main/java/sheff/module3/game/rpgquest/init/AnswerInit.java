package sheff.module3.game.rpgquest.init;

import sheff.module3.game.rpgquest.entity.Answer;
import sheff.module3.game.rpgquest.repository.LocationRepo;
import sheff.module3.game.rpgquest.repository.QuestionRepo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class AnswerInit {

    @Getter
    private final List<Answer> answers = new ArrayList<>();

    public AnswerInit(LocationRepo locationRepo, QuestionRepo questionRepo) {

        final String HELLO = "Приветствовать";
        final String EXIT = "Уйти";
        final String QUESTION = "Некоторые локации закрыты, как быть?";
        final String NEED_TO = "Мне нужно в ";
        final String THANKS = "Спасибо! Буду должен";
        final String COME_BACK_LATER = "Пока не надо, зайду позже";

        answers.add(new Answer(1, HELLO, questionRepo.getById(1), questionRepo.getById(3)));
        answers.add(new Answer(2, EXIT, questionRepo.getById(1), questionRepo.getById(2)));
        answers.add(new Answer(3, QUESTION, questionRepo.getById(4), questionRepo.getById(5)));
        answers.add(new Answer(4, EXIT, questionRepo.getById(4), questionRepo.getById(2)));
        answers.add(new Answer(5, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(3), questionRepo.getById(4)));
        answers.add(new Answer(6, EXIT, questionRepo.getById(3), questionRepo.getById(2)));
        answers.add(new Answer(7, THANKS, questionRepo.getById(5), questionRepo.getById(6)));
        answers.add(new Answer(8, COME_BACK_LATER, questionRepo.getById(5), questionRepo.getById(2)));

        answers.add(new Answer(9, HELLO, questionRepo.getById(7), questionRepo.getById(9)));
        answers.add(new Answer(10, EXIT, questionRepo.getById(7), questionRepo.getById(8)));
        answers.add(new Answer(11, QUESTION, questionRepo.getById(10), questionRepo.getById(11)));
        answers.add(new Answer(12, EXIT, questionRepo.getById(10), questionRepo.getById(8)));
        answers.add(new Answer(13, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(9), questionRepo.getById(10)));
        answers.add(new Answer(14, EXIT, questionRepo.getById(9), questionRepo.getById(8)));
        answers.add(new Answer(15, THANKS, questionRepo.getById(11), questionRepo.getById(12)));
        answers.add(new Answer(16, COME_BACK_LATER, questionRepo.getById(11), questionRepo.getById(8)));

        answers.add(new Answer(17, HELLO, questionRepo.getById(13), questionRepo.getById(15)));
        answers.add(new Answer(18, EXIT, questionRepo.getById(13), questionRepo.getById(14)));
        answers.add(new Answer(19, QUESTION, questionRepo.getById(16), questionRepo.getById(17)));
        answers.add(new Answer(20, EXIT, questionRepo.getById(16), questionRepo.getById(14)));
        answers.add(new Answer(21, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(15), questionRepo.getById(16)));
        answers.add(new Answer(22, EXIT, questionRepo.getById(15), questionRepo.getById(14)));
        answers.add(new Answer(23, THANKS, questionRepo.getById(17), questionRepo.getById(18)));
        answers.add(new Answer(24, COME_BACK_LATER, questionRepo.getById(17), questionRepo.getById(14)));

        answers.add(new Answer(25, HELLO, questionRepo.getById(19), questionRepo.getById(21)));
        answers.add(new Answer(26, EXIT, questionRepo.getById(19), questionRepo.getById(20)));
        answers.add(new Answer(27, QUESTION, questionRepo.getById(22), questionRepo.getById(23)));
        answers.add(new Answer(28, EXIT, questionRepo.getById(22), questionRepo.getById(20)));
        answers.add(new Answer(29, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(21), questionRepo.getById(22)));
        answers.add(new Answer(30, EXIT, questionRepo.getById(21), questionRepo.getById(20)));
        answers.add(new Answer(31, THANKS, questionRepo.getById(23), questionRepo.getById(24)));
        answers.add(new Answer(32, COME_BACK_LATER, questionRepo.getById(23), questionRepo.getById(20)));

        answers.add(new Answer(33, HELLO, questionRepo.getById(25), questionRepo.getById(27)));
        answers.add(new Answer(34, EXIT, questionRepo.getById(25), questionRepo.getById(26)));
        answers.add(new Answer(35, QUESTION, questionRepo.getById(28), questionRepo.getById(29)));
        answers.add(new Answer(36, EXIT, questionRepo.getById(28), questionRepo.getById(26)));
        answers.add(new Answer(37, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(27), questionRepo.getById(28)));
        answers.add(new Answer(38, EXIT, questionRepo.getById(27), questionRepo.getById(26)));
        answers.add(new Answer(39, THANKS, questionRepo.getById(29), questionRepo.getById(30)));
        answers.add(new Answer(40, COME_BACK_LATER, questionRepo.getById(29), questionRepo.getById(26)));

        answers.add(new Answer(41, HELLO, questionRepo.getById(31), questionRepo.getById(33)));
        answers.add(new Answer(42, EXIT, questionRepo.getById(31), questionRepo.getById(32)));
        answers.add(new Answer(43, QUESTION, questionRepo.getById(34), questionRepo.getById(35)));
        answers.add(new Answer(44, EXIT, questionRepo.getById(34), questionRepo.getById(32)));
        answers.add(new Answer(45, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(33), questionRepo.getById(34)));
        answers.add(new Answer(46, EXIT, questionRepo.getById(33), questionRepo.getById(32)));
        answers.add(new Answer(47, THANKS, questionRepo.getById(35), questionRepo.getById(36)));
        answers.add(new Answer(48, COME_BACK_LATER, questionRepo.getById(35), questionRepo.getById(32)));

        answers.add(new Answer(49, HELLO, questionRepo.getById(37), questionRepo.getById(39)));
        answers.add(new Answer(50, EXIT, questionRepo.getById(37), questionRepo.getById(38)));
        answers.add(new Answer(51, QUESTION, questionRepo.getById(40), questionRepo.getById(41)));
        answers.add(new Answer(52, EXIT, questionRepo.getById(40), questionRepo.getById(38)));
        answers.add(new Answer(53, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(39), questionRepo.getById(40)));
        answers.add(new Answer(54, EXIT, questionRepo.getById(39), questionRepo.getById(38)));
        answers.add(new Answer(55, THANKS, questionRepo.getById(41), questionRepo.getById(42)));
        answers.add(new Answer(56, COME_BACK_LATER, questionRepo.getById(41), questionRepo.getById(38)));

        answers.add(new Answer(57, HELLO, questionRepo.getById(43), questionRepo.getById(45)));
        answers.add(new Answer(58, EXIT, questionRepo.getById(43), questionRepo.getById(44)));
        answers.add(new Answer(59, QUESTION, questionRepo.getById(46), questionRepo.getById(47)));
        answers.add(new Answer(60, EXIT, questionRepo.getById(46), questionRepo.getById(44)));
        answers.add(new Answer(61, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(45), questionRepo.getById(46)));
        answers.add(new Answer(62, EXIT, questionRepo.getById(45), questionRepo.getById(44)));
        answers.add(new Answer(63, THANKS, questionRepo.getById(47), questionRepo.getById(48)));
        answers.add(new Answer(64, COME_BACK_LATER, questionRepo.getById(47), questionRepo.getById(44)));

        answers.add(new Answer(65, HELLO, questionRepo.getById(49), questionRepo.getById(51)));
        answers.add(new Answer(66, EXIT, questionRepo.getById(49), questionRepo.getById(50)));
        answers.add(new Answer(67, QUESTION, questionRepo.getById(52), questionRepo.getById(53)));
        answers.add(new Answer(68, EXIT, questionRepo.getById(52), questionRepo.getById(50)));
        answers.add(new Answer(69, NEED_TO + locationRepo.getById(14).getName(), questionRepo.getById(51), questionRepo.getById(52)));
        answers.add(new Answer(70, EXIT, questionRepo.getById(51), questionRepo.getById(50)));
        answers.add(new Answer(71, THANKS, questionRepo.getById(53), questionRepo.getById(54)));
        answers.add(new Answer(72, COME_BACK_LATER, questionRepo.getById(53), questionRepo.getById(50)));
    }
}
