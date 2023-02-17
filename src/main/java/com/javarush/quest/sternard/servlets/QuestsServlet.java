package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.service.QuestService;
import com.javarush.quest.sternard.util.Attribute;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import com.javarush.quest.sternard.util.Paginator;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.javarush.quest.sternard.util.CommonCode.badParams;
import static com.javarush.quest.sternard.util.CommonCode.getCheckedParams;
import static com.javarush.quest.sternard.util.Parameter.PAGE_NUMBER;

@Slf4j
@WebServlet(name = "QuestsServlet", value = Page.QUESTS_SERVLET)
public class QuestsServlet extends HttpServlet {
    private final QuestService questService = QuestService.INSTANCE;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> checkedParamsMap = getCheckedParams(request, List.of());
        if (checkedParamsMap != null) {
            Collection<Quest> allQuests = questService.getAllEntities();

            String pageNum = checkedParamsMap.get(PAGE_NUMBER);
            int questsRecordsPerPagePaginator = Settings.get().getQuestsRecordsPerPagePaginator();
            List<Quest> questsOnPage = Paginator.getEntitiesOnPage(request, allQuests, pageNum, questsRecordsPerPagePaginator);

            request.setAttribute(Attribute.QUESTS, questsOnPage);
            Go.forward(request, response, Page.QUESTS_JSP);
        } else {
            badParams(request, response, request.getParameterMap(), Page.LOGIN_SERVLET);
        }
    }

}