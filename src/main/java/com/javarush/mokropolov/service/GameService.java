package com.javarush.mokropolov.service;

import com.javarush.mokropolov.entity.*;
import com.javarush.mokropolov.repository.*;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.Optional;

@AllArgsConstructor 
public class GameService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final QuestRepository questRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public Optional<Game> getGame(Long questId, Long userId) {
        Game gamePattern = Game.builder().questId(questId).build();
        gamePattern.setGameState(GameState.PLAY);
        gamePattern.setUserId(userId);
        Optional<Game> currentGame = gameRepository
                .find(gamePattern)
                .max(Comparator.comparingLong(Game::getId));
        if (currentGame.isPresent()) {
            return currentGame;
        } else if (gamePattern.getQuestId() != null) {
            return Optional.of(getNewGame(userId, gamePattern.getQuestId()));
        } else {
            return Optional.empty();
        }
    }

    private Game getNewGame(Long userId, Long questId) {
        Quest quest = questRepository.get(questId);
        Long startQuestionId = quest.getStartQuestionId();
        Question firstQuestion = questionRepository.get(startQuestionId);
        Game newGame = Game.builder()
                .questId(questId)
                .currentQuestionId(startQuestionId)
                .gameState(firstQuestion.getGameState())
                .userId(userId)
                .build();
        userRepository.get(userId).getGames().add(newGame);
        gameRepository.create(newGame);
        return newGame;
    }

    public Optional<Game> checkAnswer(Long gameId, Long answerId) {
        Game game = gameRepository.get(gameId);
        if (game.getGameState() == GameState.PLAY) {
            Answer answer = answerRepository.get(answerId);
            Long nextQuestionId = answer != null
                    ? answer.getNextQuestionId()
                    : game.getCurrentQuestionId();
            game.setCurrentQuestionId(nextQuestionId);
            Question question = questionRepository.get(nextQuestionId);
            game.setGameState(question.getGameState());
            gameRepository.update(game);
        } else {
            game = getNewGame(game.getUserId(), game.getQuestId());
        }
        return Optional.ofNullable(game);
    }

}
