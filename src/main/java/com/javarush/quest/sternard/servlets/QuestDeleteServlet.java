package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.service.QuestService;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.javarush.quest.sternard.util.CommonCode.checkedParamsFromJson;
import static com.javarush.quest.sternard.util.Parameter.ID;
import static com.javarush.quest.sternard.util.message.LoggerMessages.*;

@Slf4j
@WebServlet(name = "QuestDeleteServlet", value = Page.DELETE_QUEST_SERVLET)
public class QuestDeleteServlet extends HttpServlet {
    private final QuestService questService = QuestService.INSTANCE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info(USER_PERMISSION_DENIED_TO_PAGE, Page.DELETE_QUEST_SERVLET);
        Go.redirect(request, response, Page.QUESTS_SERVLET);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> checkedParamsMap = checkedParamsFromJson(req, List.of(ID));

        if (checkedParamsMap != null) {
            long questId = Long.parseLong(checkedParamsMap.get(ID));
            Quest quest = questService.getEntitiesMap().get(questId);

            if (quest != null) {
                log.info(QUEST_WAS_DELETED, quest.getTitle());
                questService.delete(questId);
            } else {
                log.warn(QUEST_SUBSTITUTION_PARAMETERS, questId);
                resp.setStatus(404);
            }
        } else {
            log.warn(BAD_PARAMETERS, req.getParameterMap());
            resp.setStatus(404);
        }
    }

}