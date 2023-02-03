package com.bogdanov.service;

import com.bogdanov.entity.Question;

import com.bogdanov.repository.QuestionRepository;
import com.bogdanov.repository.Repository;


import java.util.Collection;
import java.util.Optional;


public enum QuestionService {
    QUESTION_SERVICE;

    private final Repository<Question> questionRepository = new QuestionRepository();


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
        return Optional.ofNullable(questionRepository.get(id));
   }
    public Optional<Question> get(String questId, String id){
            Question question = Question
                    .builder()
                    .id(Long.valueOf(id))
                    .questId(Long.valueOf(questId))
                    .build();

       return questionRepository.find(question).findAny();
    }

}
