package com.bogdanov.service;

import com.bogdanov.entity.Answer;
import com.bogdanov.entity.Question;

import com.bogdanov.repository.QuestionRepository;
import com.bogdanov.repository.Repository;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public enum QuestionService {
    QUESTION_SERVICE;

    private final Repository<Question> questionRepository = new QuestionRepository();
    final AnswerService answerService = AnswerService.ANSWER_SERVICE;


    public void create(Question question){
        questionRepository.create(question);
    }
    public void update(Question question){

        questionRepository.update(question);
    }
    public void delete(Question question){
        questionRepository.delete(question);
    }
   public Collection<Question> getAll(){
        return questionRepository.getAll();
   }


   public Optional<Question> get(Long id){
        List<Answer> ofQuestion = answerService.getOfQuestion(id);
       Question question = questionRepository.get(id);
       question.getAnswers().clear();
       question.getAnswers().addAll(ofQuestion);
       return Optional.of(question);
   }

    public Optional<Question> get(String questId, String id){
            Question question = Question
                    .builder()
                    .id(Long.valueOf(id))
                    .questId(Long.valueOf(questId))
                    .build();

       return questionRepository.find(question).findAny();
    }
    public List<Question> getOfQuest(Long id){
        List<Question> questions = questionRepository
                .getAll()
                .stream()
                .filter(u -> Objects.equals(u.getQuestId(), id))
                .toList();
                questions.forEach(u->this.get(u.getIdQuestion()));
                return questions;
    }

}
