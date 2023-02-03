package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.service.ImageService;
import com.javarush.quest.sternard.service.QuestService;
import com.javarush.quest.sternard.service.UserService;
import com.javarush.quest.sternard.util.Attribute;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.javarush.quest.sternard.util.CommonCode.*;
import static com.javarush.quest.sternard.util.Parameter.*;
import static com.javarush.quest.sternard.util.message.LoggerMessages.QUEST_WAS_UPDATED;
import static com.javarush.quest.sternard.util.message.LoggerMessages.QUEST_WRONG_PARAMETERS_OR_DENIED_SYMBOLS;

@Slf4j
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5)
@WebServlet(name = "EditQuestServlet", value = Page.EDIT_QUEST_SERVLET)
public class QuestEditServlet extends HttpServlet {
    private final QuestService questService = QuestService.INSTANCE;
    private final UserService userService = UserService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> checkedParamsMap = getCheckedParams(request, List.of(ID));
        if (checkedParamsMap != null) {
            Long questId = Long.parseLong(checkedParamsMap.get(ID));
            Quest quest = questService.getEntitiesMap().get(questId);
            Collection<User> users = userService.getAllEntities();

            if (quest != null) {
                request.setAttribute(Attribute.QUEST, quest);
                request.setAttribute(Attribute.USERS, users);
                Go.forward(request, response, Page.EDIT_QUEST_JSP);
            } else {
                questSubstitutionParams(request, response, questId);
            }
        } else {
            badParams(request, response, request.getParameterMap(), Page.QUESTS_SERVLET);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> requiredParams = List.of(ID, TITLE, DESCRIPTION, IMAGE, CREATION_DATE, VIEWS, RATING, AUTHOR);
        Map<String, String> checkedParamsMap = getCheckedParams(req, requiredParams);

        if (checkedParamsMap != null) {
            Long questId = Long.parseLong(checkedParamsMap.get(ID));
            Quest quest = questService.getEntitiesMap().get(questId);

            if (quest != null)
                tryToUpdateQuest(req, resp, checkedParamsMap, questId, quest);
            else
                questSubstitutionParams(req, resp, questId);

        } else {
            badParams(req, resp, req.getParameterMap(), Page.QUESTS_SERVLET);
        }
    }

    private void tryToUpdateQuest(HttpServletRequest req, HttpServletResponse resp, Map<String, String> checkedParamsMap,
                                  Long questId, Quest quest) throws ServletException, IOException {
        String questImage = quest.getImage();

        Map<String, String> imagesSubFolders = Settings.get().getImagesSubFolders();
        String imageQuestFolder = imagesSubFolders.get("quest");

        boolean isUploadedImage = imageService.uploadImage(req, questImage, imageQuestFolder);
        boolean isUpdatedQuest = questService.update(quest, checkedParamsMap);

        if (isUploadedImage) {
            if (isUpdatedQuest)
                log.info(QUEST_WAS_UPDATED, questId);
            else
                log.info(QUEST_WRONG_PARAMETERS_OR_DENIED_SYMBOLS, questId);
        }
        Go.redirect(req, resp, Page.EDIT_QUEST_SERVLET + "?id=" + questId);
    }

}