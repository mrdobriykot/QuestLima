package com.javarush.quest.marzhiievskyi.service;

import com.javarush.quest.marzhiievskyi.entity.*;
import com.javarush.quest.marzhiievskyi.repository.GameSessionRepository;
import com.javarush.quest.marzhiievskyi.repository.QuestRepository;
import com.javarush.quest.marzhiievskyi.repository.Repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public enum GameSessionService {
    GAME_SESSION_SERVICE;

    private final Repository<GameSession> gameSessionRepository = new GameSessionRepository();
    private final Repository<Quest> questRepository = new QuestRepository();
    private final UserService userService = UserService.USER_SERVICE;



    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public Quest getQuest(Long questId) {
        return questRepository.get(questId);
    }

    public Collection<Quest> getAllQuests() {
        return questRepository.getAll();
    }

    public Optional<Question> getQuestion(Long questId, Long questionId) {
        Quest quest = questRepository.get(questId);
        Collection<Question> questions = quest.getQuestions();
        return questions.stream().filter(q -> Objects.equals(q.getId(), questionId)).findAny();
    }

    public Collection<Answer> getAnswers(Long questId, Long questionId) {
        Optional<Question> question = getQuestion(questId, questionId);
        return question.map(Question::getAnswerList).orElse(null);
    }

    public GameState checkEndOfTheGame(Long userId, Long questionId, Long questId) {
        Optional<Question> optionalQuestion = getQuestion(questId, questionId);
        GameState result = GameState.PLAY;
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            GameState gameState = question.getGameState();
            if (gameState == GameState.LOST || gameState == GameState.WIN) {
                GameSession playedGameByUser = GameSession.builder()
                        .id(0L)
                        .userId(userId)
                        .questId(questId)
                        .currentQuestionId(question.getId())
                        .gameState(gameState)
                        .build();
                gameSessionRepository.create(playedGameByUser);
                writeGameToUserProfile(userId, playedGameByUser);
                result = gameState;
            }
        }
        return result;
    }

    private void writeGameToUserProfile(Long userId, GameSession playedGameByUser) {
        Optional<User> optionalUser = userService.get(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Collection<GameSession> games = user.getGames();
            games.add(playedGameByUser);
            userService.update(user);
        }
    }

}
