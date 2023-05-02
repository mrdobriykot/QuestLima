package com.javarush.plugin.config;

import com.javarush.plugin.entity.Answer;
import com.javarush.plugin.entity.GameState;
import com.javarush.plugin.entity.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;


public final class Config {
    JSONParser parser = new JSONParser();
    Question question = new Question();
    Collection<Question> arrayList = new ArrayList<>();
    Collection<Answer> answerCollection = new ArrayList<>();

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("QuestText/questions.json");


    public Collection<Question> jsonQuestionArrayParser(String path) throws IOException {

        try (FileInputStream fis = new FileInputStream(path);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object object : jsonArray) {
                JSONObject jsonQuestion = (JSONObject) object;

                Question question = new Question();

                Long id = (Long) jsonQuestion.get("id");
                question.setId(id);

                String text = (String) jsonQuestion.get("text");
                question.setText(text);

                String gameState = (String) jsonQuestion.get("gameState");
                question.setGameState(GameState.valueOf(gameState));

                arrayList.add(question);
            }

            return arrayList;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Answer> jsonAnswerArrayParser(String path) throws IOException {

        try (FileInputStream fis = new FileInputStream(path);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object object : jsonArray) {
                JSONObject jsonAnswer = (JSONObject) object;

                Answer answer = new Answer();

                Long id = (Long) jsonAnswer.get("id");
                answer.setId(id);

                String text = (String) jsonAnswer.get("text");
                answer.setText(text);

                Long questionId = (Long) jsonAnswer.get("questionId");
                answer.setQuestionId(questionId);

                Long nextQuestionId = (Long) jsonAnswer.get("nextQuestionId");
                answer.setNextQuestionId(nextQuestionId);

                answerCollection.add(answer);
            }

            return answerCollection;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
