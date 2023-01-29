package sheff.module3.game.rpgquest.servlet;

import sheff.module3.game.rpgquest.entity.*;
import sheff.module3.game.rpgquest.init.*;
import sheff.module3.game.rpgquest.repository.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        UserRepo userRepo = new UserRepo();
        ctx.setAttribute("userRepo", userRepo);

        LocationInit locationInit = new LocationInit();
        LocationRepo locationRepo = new LocationRepo(locationInit);
        for (Location location : locationInit.getLocations()) {
            locationRepo.save(location.getId(), location);
        }
        ctx.setAttribute("locationRepo", locationRepo);


        LocationKeyInit locationKeyInit = new LocationKeyInit(locationRepo);
        LocationKeyRepo locationKeyRepo = new LocationKeyRepo();
        for (LocationKey locationKey : locationKeyInit.getLocationKeys()) {
            locationKeyRepo.save(locationKey.getId(), locationKey);
        }
        ctx.setAttribute("locationKeyRepo", locationKeyRepo);


        QuestionInit questionInit = new QuestionInit(locationRepo);
        QuestionRepo questionRepo = new QuestionRepo();
        for (Question question : questionInit.getQuestions()) {
            questionRepo.save(question.getId(), question);
        }
        ctx.setAttribute("questionRepo", questionRepo);


        AnswerInit answerInit = new AnswerInit(locationRepo, questionRepo);
        AnswerRepo answerRepo = new AnswerRepo();
        for (Answer answer : answerInit.getAnswers()) {
            answerRepo.save(answer.getId(), answer);
        }
        ctx.setAttribute("answerRepo", answerRepo);


        PersonInit personInit = new PersonInit(locationRepo, questionRepo, locationKeyRepo);
        PersonRepo personRepo = new PersonRepo();
        for (Person person : personInit.getPersons()) {
            personRepo.save(person.getId(), person);
        }
        ctx.setAttribute("personRepo", personRepo);
    }
}
