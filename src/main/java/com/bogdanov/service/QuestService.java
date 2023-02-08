package com.bogdanov.service;

import com.bogdanov.entity.Answer;
import com.bogdanov.entity.Quest;
import com.bogdanov.entity.Question;
import com.bogdanov.repository.QuestRepository;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

public enum QuestService {
    QUEST_SERVICE;

    QuestRepository  questRepository = new QuestRepository();
    QuestionService questionService = QuestionService.QUESTION_SERVICE;
    AnswerService answerService = AnswerService.ANSWER_SERVICE;




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
                
                if(question.getId()==answer.getQuestionId()){
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
        return Optional.ofNullable(questRepository.get(id));
   }
    public List<Quest> getOfAutor(Long id){
        return questRepository.getAll().stream().filter(u-> u.getAuthorId()==id).toList();

    }

    public void parsAnswers(String answer,String question){

    }
}
