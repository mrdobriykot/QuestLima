package com.javarush.quest.sternard.repository.mysql;

import com.javarush.quest.sternard.entities.Answer;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.entities.QuestState;
import com.javarush.quest.sternard.entities.Question;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.javarush.quest.sternard.repository.Repository;
import com.javarush.quest.sternard.util.database.JdbcConnector;
import com.nqadmin.rowset.JdbcRowSetImpl;
import lombok.extern.slf4j.Slf4j;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Date;
import java.sql.*;
import java.util.*;

import static com.javarush.quest.sternard.util.database.DbFields.*;
import static com.javarush.quest.sternard.util.database.DbQueries.*;

@Slf4j
public class QuestRepository implements Repository<Quest> {

    public QuestRepository() {
        this.getEntitiesMap();
    }

    @Override
    public void create(Quest entity) {

    }

    @Override
    public void update(Quest entity) {
        try (Connection connection = JdbcConnector.INSTANCE.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(MYSQL_UPDATE_QUEST)) {

            prepStmt.setString(1, entity.getTitle());
            prepStmt.setString(2, entity.getDescription());
            prepStmt.setString(3, entity.getImage());
            prepStmt.setDate(4, Date.valueOf(entity.getCreationDate()));
            prepStmt.setInt(5, entity.getViews());
            prepStmt.setDouble(6, entity.getRating());
            prepStmt.setString(7, entity.getAuthor());
            prepStmt.setLong(8, entity.getId());
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = JdbcConnector.INSTANCE.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(MYSQL_DELETE_QUEST)) {
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public Collection<Quest> getAllEntities() {
        return getEntitiesMap().values();
    }

    private List<Answer> getAnswers(long questionId, Connection connection) {
        List<Answer> answers = new ArrayList<>();
        try (PreparedStatement prepStmt = connection.prepareStatement(MYSQL_GET_ANSWERS_BY_QUESTION_ID)) {

            prepStmt.setLong(1, questionId);
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                Answer answer = buildAnswer(resultSet);
                answers.add(answer);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
        return answers;
    }

    private Map<Long, Question> getQuestions(long questId, Connection connection) {
        Map<Long, Question> map = new HashMap<>();
        try (PreparedStatement prepStmt = connection.prepareStatement(MYSQL_GET_QUESTIONS_BY_QUEST_ID)) {

            prepStmt.setLong(1, questId);
            ResultSet resultSet = prepStmt.executeQuery();

            while (resultSet.next()) {
                long question_id = resultSet.getLong(ID);
                Question question = buildQuestion(questId, resultSet, question_id, connection);
                map.put(question_id, question);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
        return map;
    }

    @Override
    public Map<Long, Quest> getEntitiesMap() {
        Map<Long, Quest> map = new HashMap<>();
        try (Connection connection = JdbcConnector.INSTANCE.getConnection();
             JdbcRowSet rowSet = new JdbcRowSetImpl(connection)) {

            rowSet.setType(ResultSet.TYPE_SCROLL_SENSITIVE);
            rowSet.setCommand(MYSQL_GET_ALL_QUESTS);
            rowSet.execute();

            while (rowSet.next()) {
                long questId = rowSet.getLong(ID);
                Quest quest = buildQuest(rowSet, questId, connection);
                map.put(questId, quest);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
        return map;
    }

    private Answer buildAnswer(ResultSet resultSet) throws SQLException {
        return Answer.builder()
                .id(resultSet.getLong(ID))
                .answer(resultSet.getString(ANSWER))
                .nextQuestionId(resultSet.getInt(NEXT_QUESTION_ID))
                .build();
    }

    private Question buildQuestion(long questId, ResultSet resultSet, long question_id, Connection connection) throws SQLException {
        String state = resultSet.getString(STATE);
        return Question.builder()
                .questId(questId)
                .text(resultSet.getString(TEXT))
                .questState(QuestState.valueOf(state))
                .image(resultSet.getString(IMAGE))
                .answers(getAnswers(question_id, connection))
                .build();
    }

    private Quest buildQuest(JdbcRowSet rowSet, long questId, Connection connection) throws SQLException {
        return Quest.builder()
                .id(questId)
                .title(rowSet.getString(TITLE))
                .description(rowSet.getString(DESCRIPTION))
                .image(rowSet.getString(IMAGE))
                .views(rowSet.getInt(VIEWS))
                .rating(rowSet.getDouble(RATING))
                .author(rowSet.getString(AUTHOR))
                .creationDate(rowSet.getDate(CREATION_DATE).toLocalDate())
                .firstQuestionId(rowSet.getInt(FIRST_QUESTION_ID))
                .question(getQuestions(questId, connection))
                .build();
    }

}