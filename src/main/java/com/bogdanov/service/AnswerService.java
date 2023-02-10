package com.bogdanov.service;

import com.bogdanov.entity.Answer;

import com.bogdanov.entity.Question;
import com.bogdanov.repository.AnswerRepository;
import com.bogdanov.repository.Repository;



import java.util.Collection;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


public enum AnswerService {
    ANSWER_SERVICE;

    private final Repository<Answer> answerRepository = new AnswerRepository();


    public void create(Answer answer){
        answerRepository.create(answer);
    }
    public void update(Answer answer){

        answerRepository.update(answer);
    }
    public void delete(Answer answer){
        answerRepository.delete(answer);
    }
   public Collection<Answer> getAll(){
        return answerRepository.getAll();
   }
   public Optional<Answer> get(Long id){
        return Optional.ofNullable(answerRepository.get(id));
   }
    public Optional<Answer> get(String questionId, String id){
            Answer answer = Answer
                    .builder()
                    .questionId(Long.valueOf(questionId))
                    .id(Long.getLong(id))
                    .build();

       return answerRepository.find(answer).findAny();
    }
    public List<Answer> getOfQuestion(Long id){
        return answerRepository.getAll().stream().filter(u-> Objects.equals(u.getQuestionId(), id)).toList();
    }

//    public Collection<Answer> parsAnswers(String answer){
//        ArrayList<String> answersStr = new ArrayList<>();
//        ArrayList<Answer> answers = new ArrayList<>();
//        Pattern answerPattern = Pattern.compile("\\b[0-9]+\\.[0-9]+\\..+");
//        Matcher matcherAns = answerPattern.matcher(answer);
//
//        while (matcherAns.find()) {
//            answersStr.add(matcherAns.group());
//
//        }
//        for (String s : answersStr) {
//            String[] split = s.split(".");
//            Answer answer1 = Answer.builder()
//                    .questionId(Long.valueOf(split[0]))
//                    .id(Long.valueOf(split[1]))
//                    .text(split[2])
//                    .status(Objects.equals(split[3], "T"))
//                    .build();
//            answers.add(answer1);
//        }
//        return answers;
//
//    }
}
