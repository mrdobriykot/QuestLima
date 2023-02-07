package com.javarush.quest.ivanilov.listeners;

import com.javarush.quest.ivanilov.config.Config;
import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.constants.Logs;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebListener
public class InitListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        QuestParser parser = new QuestParser(QuestService.QUEST_SERVICE,
                EventService.EVENT_SERVICE,
                TaskService.TASK_SERVICE,
                HeroService.HERO_SERVICE);
        Config cfg = new Config(parser, UserService.USER_SERVICE);
        cfg.readUsersAndQuestsFromResources();
        log.info(Logs.APP_INIT);
    }
}
