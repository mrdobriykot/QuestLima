package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.repository.ChooserQuestsRepository;
import com.javarush.quest.sternard.repository.ChooserUsersRepository;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.javarush.quest.sternard.listener.Listener.questRepository;
import static com.javarush.quest.sternard.listener.Listener.userRepository;
import static com.javarush.quest.sternard.util.Attribute.QUESTS_REPOSITORIES;
import static com.javarush.quest.sternard.util.Attribute.USERS_REPOSITORIES;
import static com.javarush.quest.sternard.util.Page.EDIT_SETTINGS_SERVLET;
import static com.javarush.quest.sternard.util.Parameter.SWITCH_QUEST_REPOS;
import static com.javarush.quest.sternard.util.Parameter.SWITCH_USER_REPOS;
import static com.javarush.quest.sternard.util.message.LoggerMessages.CHANGE_REPOSITORY_USERS_AND_QUESTS;

@Slf4j
@WebServlet(name = "SettingsEditServlet", value = EDIT_SETTINGS_SERVLET)
public class SettingsEditServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ChooserQuestsRepository[] quests = ChooserQuestsRepository.values();
        ChooserUsersRepository[] users = ChooserUsersRepository.values();

        request.setAttribute(QUESTS_REPOSITORIES, quests);
        request.setAttribute(USERS_REPOSITORIES, users);

        Go.forward(request, response, Page.EDIT_SETTINGS_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String switchQuestRepos = req.getParameter(SWITCH_QUEST_REPOS);
            String switchUserRepos = req.getParameter(SWITCH_USER_REPOS);

            questRepository = ChooserQuestsRepository
                    .valueOf(switchQuestRepos)
                    .getRepository();

            userRepository = ChooserUsersRepository
                    .valueOf(switchUserRepos)
                    .getRepository();

            log.info(CHANGE_REPOSITORY_USERS_AND_QUESTS, switchUserRepos, switchQuestRepos);
            doGet(req, resp);

        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            Go.redirect(req, resp, Page.HOME_SERVLET);
        }
    }

}