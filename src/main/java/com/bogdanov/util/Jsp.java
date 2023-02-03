package com.bogdanov.util;

import com.bogdanov.entity.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Jsp {
    public static void forward(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        target = target.replace("/","");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/%s.jsp".formatted(target));
        requestDispatcher.forward(request, response);
    }
    public static User getUserCunstructor(HttpServletRequest request) {
        return User.builder()
                .id(Long.valueOf(request.getParameter(Key.ID)))
                .login(request.getParameter(Key.LOGIN))
                .password(request.getParameter(Key.PASSWORD))
                .role(Role.valueOf(request.getParameter(Key.ROLE)))
                .build();
    }

    public static Quest getQuestCunstructor(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Key.USER);
        String questDescription = request.getParameter("questDescription");
         Quest quest = Quest
                .builder()
                .id(Long.valueOf(request.getParameter(Key.ID)))
                .authorId(user.getId())
                .name((String) request.getParameter(Key.QUESTS))
                .text((String) questDescription)
                .build();
         quest.setAnswerText(request.getParameter(Key.ANSWER_TEXT));
         quest.setQuestionText(request.getParameter(Key.QUESTION_TEXT));
        return quest;
    }
    public static Collection<Question> getQuestionCunstructor(HttpServletRequest request) {
        ArrayList<String> questionsStr = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();

        Pattern questionPattern = Pattern.compile("\\b[0-9]+\\.+[a-zA-Zа-яА-Я]+.+");

        Matcher matcherQuest = questionPattern.matcher(request.getParameter(Key.QUESTION_TEXT));
        while (matcherQuest.find()) {
            questionsStr.add(matcherQuest.group());
        }
        for (String s : questionsStr) {
            String[] split = s.split("\\.");
            Question question = Question
                    .builder()
                    .id(Long.valueOf(split[0]))
                    .idQuestion(Long.valueOf(split[0]))
                    .text(split[1])
                    .build();
            questions.add(question);

        }

        return questions;
    }
    public static Collection<Answer> getAnswerCunstructor(HttpServletRequest request) {
        ArrayList<String> answersStr = new ArrayList<>();
        ArrayList<Answer> answers = new ArrayList<>();
        Pattern answerPattern = Pattern.compile("\\b[0-9]+\\.[0-9]+\\..+");

        Matcher matcherAns = answerPattern.matcher(request.getParameter(Key.ANSWER_TEXT));

        while (matcherAns.find()) {
            answersStr.add(matcherAns.group());
        }

        for (String s : answersStr) {
            String[] split = s.split("\\.");
            Answer answer1 = Answer.builder()
                    .questionId(Long.valueOf(split[0]))
                    .idAnswer(Long.valueOf(split[1]))
                    .text(split[2])
                    .status(Objects.equals(split[split.length-1], "T"))
                    .nextQuestionId(split[split.length-1]=="T"? Long.valueOf(split[0]+1) :null)
                    .build();
            answers.add(answer1);

        }
        return answers;
    }
    public static void response(HttpServletResponse response, String target) throws IOException {
        target = target.replace("/","");
        response.sendRedirect(target);
    }

}
