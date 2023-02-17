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
        updateIdOfAnswerQuestionQuest(questionCunstructor,answerCunstructor,quest);

    }
    public void update(Quest quest, Collection<Question> questionCunstructor, Collection<Answer> answerCunstructor){
        updateIdOfAnswerQuestionQuest(questionCunstructor,answerCunstructor,quest);
        questRepository.update(quest);
    }
    private void updateIdOfAnswerQuestionQuest(Collection<Question> questions, Collection<Answer> answers, Quest quest){
        for (Question question : questions) {

            for (Answer answer : answers) {

                if(question.getIdQuestion()==answer.getQuestionId()){
                    //question.getAnswers().add(answer);
                    answerService.create(answer);
                    question.getIdAnswers().add(answer.getId());
                }
            }
            questionService.create(question);
            question.setQuestId(quest.getId());
            quest.getIdQuestions().add(question.getId());

        }

    }
    public void delete(Quest quest){
        questRepository.delete(quest);
    }
   public Collection<Quest> getAll(){
        return questRepository.getAll();
   }

   public Optional<Quest> get(Long id){
       Quest quest = questRepository.get(id);

       if(quest.getQuestions().size()>0){
           return Optional.ofNullable(quest);
       }else {
           List<Question> listQuestionsForQuest = questionService.getOfQuest(quest);
           quest.getQuestions().addAll(listQuestionsForQuest);
           return Optional.ofNullable(quest);
       }
   }
   public Optional<Quest> getQuestFromRep(Long id){
        return Optional.ofNullable(questRepository.get(id));
   }


    public Collection<Quest> getOfAutor(Long id) {
        Quest quest = new Quest();
        quest.setAuthorId(id);
        return questRepository.find(quest).toList();
    }
}
