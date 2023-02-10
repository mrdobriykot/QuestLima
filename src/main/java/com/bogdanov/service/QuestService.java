package com.bogdanov.service;

import com.bogdanov.entity.Answer;
import com.bogdanov.entity.Quest;
import com.bogdanov.entity.Question;
import com.bogdanov.repository.QuestRepository;


import java.util.*;

public enum QuestService {
    QUEST_SERVICE;

     final QuestRepository  questRepository = new QuestRepository();
    final QuestionService questionService = QuestionService.QUESTION_SERVICE;
    final AnswerService answerService = AnswerService.ANSWER_SERVICE;




    public void create(Quest quest, Collection<Question> questionCunstructor, Collection<Answer> answerCunstructor){
        questRepository.create(quest);
        collectionPutAnswers(questionCunstructor,answerCunstructor,quest.getId());
        quest.getQuestions().addAll(questionCunstructor);
        questRepository.update(quest);

    }
    public void update(Quest quest, Collection<Question> questionCunstructor, Collection<Answer> answerCunstructor){
        answerService.getAll().clear();
        questionService.getAll().clear();
        collectionPutAnswers(questionCunstructor,answerCunstructor,quest.getId());
        quest.getQuestions().clear();
        quest.getQuestions().addAll(questionCunstructor);
        questRepository.update(quest);
    }
    private void collectionPutAnswers(Collection<Question> questions, Collection<Answer> answers, Long idQuests){
        for (Question question : questions) {

            question.setQuestId(idQuests);
            
            for (Answer answer : answers) {
                
                if(Objects.equals(question.getId(), answer.getQuestionId())){
                    question.getAnswers().add(answer);
                }
                answerService.create(answer);
            }
            questionService.create(question);
        }
    }
    public void delete(Quest quest){
        questRepository.delete(quest);
    }
   public Collection<Quest> getAll(){
        return questRepository.getAll();
   }

   public Optional<Quest> get(Long id){
       List<Question> ofQuest = questionService.getOfQuest(id);
       Quest quest = questRepository.get(id);
       quest.getQuestions().clear();
       quest.getQuestions().addAll(ofQuest);
       questRepository.update(quest);
        return Optional.ofNullable(quest);
   }
   public Optional<Quest> getQuestFromRep(Long id){
        return Optional.ofNullable(questRepository.get(id));
   }
    public List<Quest> getOfAutor(Long id){
        return questRepository.getAll().stream().filter(u-> Objects.equals(u.getAuthorId(), id)).toList();
    }


}
