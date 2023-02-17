package com.javarush.quest.sternard.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.entities.QuestState;
import com.javarush.quest.sternard.entities.Question;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.service.QuestService;
import com.javarush.quest.sternard.service.UserService;
import com.javarush.quest.sternard.util.Attribute;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import com.javarush.quest.sternard.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static com.javarush.quest.sternard.util.Attribute.NEXT_QUESTION_ID;
import static com.javarush.quest.sternard.util.Attribute.QUEST_ID;
import static com.javarush.quest.sternard.util.CommonCode.*;
import static com.javarush.quest.sternard.util.Parameter.*;
import static com.javarush.quest.sternard.util.message.LoggerMessages.*;

/***
 * В сессии хранится состояние квеста. Можно начать играть в один квест, потом перейти в другой квест
 * и продолжить играть. Соответственно, можно начать играть в любой квест и в любое время продолжить играть
 * если квест не закончен и сессия активна.
 * В сессии хранится: QUEST_ID + questId = ("questId1" где 1 - id квеста)
 * В сессии хранится: NEXT_QUESTION_ID + questId = ("nextQuestionId1" где 1 - id следующего вопроса)
 */

@Slf4j
@WebServlet(name = "QuestServlet", value = Page.QUEST_SERVLET)
public class QuestServlet extends HttpServlet {
    private final QuestService questService = QuestService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Map<String, String[]> params = req.getParameterMap();
        Map<String, String> checkedParamsMap = this.getCheckedParams(params, List.of(ID));
        User userSession = (User) session.getAttribute(Attribute.USER);

        if (checkedParamsMap != null) {
            Long questId = Long.parseLong(checkedParamsMap.get(ID));
            String questIdAttr = QUEST_ID + questId;
            String nextQuestionIdAttr = NEXT_QUESTION_ID + questId;
            Object questIdSession = session.getAttribute(questIdAttr);
            Object nextQuestionIdSession = session.getAttribute(nextQuestionIdAttr);

            if (ObjectUtils.allNull(questIdSession, nextQuestionIdSession))
                startPlayFirstGame(req, resp, session, userSession, questId, questIdAttr, nextQuestionIdAttr);
            else if (ObjectUtils.allNotNull(questIdSession, nextQuestionIdSession))
                continueToPlay(req, resp, questId, questIdSession, nextQuestionIdSession);
            else
                badParams(req, resp, params, Page.QUESTS_SERVLET);

        } else {
            badParams(req, resp, params, Page.QUESTS_SERVLET);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentType = req.getContentType();
        if (contentType.equals("application/json")) {
            Map<String, String> checkedParamsMap = getAndCheckParamsJson(req);
            updateRating(req, resp, checkedParamsMap);
        } else {
            tryToPlayInQuest(req, resp, req.getSession());
        }
    }

    private void tryToPlayInQuest(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException, ServletException {
        Map<String, String[]> params = req.getParameterMap();
        Map<String, String> checkedParamsMap = this.getCheckedParams(params, List.of(ID));

        if (checkedParamsMap != null) {
            Long questId = Long.parseLong(checkedParamsMap.get(ID));
            String playQuestAgainId = checkedParamsMap.get(PLAY_QUEST_AGAIN_ID);
            String nextQuestionId = checkedParamsMap.get(NEXT_QUESTION_ID);
            String questIdAttr = QUEST_ID + questId;
            String nextQuestionIdAttr = NEXT_QUESTION_ID + questId;

            if (playQuestAgainId != null) {
                playQuestAgain(req, resp, session, questId, questIdAttr, nextQuestionIdAttr);
                return;
            }

            if (ObjectUtils.anyNull(questId, nextQuestionId)) {
                log.warn(QUEST_SUBSTITUTION_ID_OR_NEXT_QUESTION_ID, questId, nextQuestionId);
                Go.redirect(req, resp, Page.QUESTS_SERVLET);
            } else
                playingQuestNow(req, resp, session, questId, questIdAttr, nextQuestionIdAttr, nextQuestionId);

        } else {
            badParams(req, resp, params, Page.QUESTS_SERVLET);
        }
    }

    private void updateRating(HttpServletRequest req, HttpServletResponse resp, Map<String, String> checkedParamsMap) throws IOException {
        Long questId = Long.parseLong(checkedParamsMap.get(ID));
        String rating = checkedParamsMap.get(RATING_UP_DOWN);

        Quest quest = questService.getEntitiesMap().get(questId);
        if (quest != null) {
            Double ratingUpdated = questService.updateRating(quest, rating);
            PrintWriter out = resp.getWriter();
            out.print(ratingUpdated);
            out.flush();
            log.info(UPDATED_RATING_IN_QUEST, questId);
        } else {
            questSubstitutionParams(req, resp, questId);
        }
    }

    private Map<String, String> getAndCheckParamsJson(HttpServletRequest req) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(inputStream);
        Map<String, String[]> params = mapper.convertValue(tree, new TypeReference<>() {
        });
        return this.getCheckedParams(params, List.of(ID, RATING_UP_DOWN));
    }

    private Map<String, String> getCheckedParams(Map<String, String[]> params, List<String> requiredParams) {
        return Validator.checkAndReturnMap(params, requiredParams);
    }

    private void playingQuestNow(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Long questId,
                                 String questIdAttr, String nextQuestionIdAttr, String nextQuestionId)
            throws ServletException, IOException {
        long nextQuestionIdLong = Long.parseLong(nextQuestionId);
        session.setAttribute(questIdAttr, questId);
        session.setAttribute(nextQuestionIdAttr, nextQuestionIdLong);
        getQuestAndSetAttributes(questId, req, resp, nextQuestionIdLong);
    }

    private void startPlayFirstGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
                                    User userSession, Long questId, String questIdAttr, String nextQuestionIdAttr)
            throws ServletException, IOException {
        Quest quest = questService.getEntitiesMap().get(questId);
        if (quest != null) {
            log.info(USER_START_PLAYING_IN_QUEST, userSession.getLogin(), questId);

            int firstQuestionId = quest.getFirstQuestionId();
            session.setAttribute(questIdAttr, questId);
            session.setAttribute(nextQuestionIdAttr, firstQuestionId);

            updateUserPlayedQuestsAndQuestViews(session, questId);
            getQuestAndSetAttributes(questId, req, resp, firstQuestionId);
        } else {
            questSubstitutionParams(req, resp, questId);
        }
    }

    private void continueToPlay(HttpServletRequest req, HttpServletResponse resp, Long questId, Object questIdSession,
                                Object nextQuestionIdSession) throws ServletException, IOException {
        if (questId.toString().equals(questIdSession.toString())) {
            long nextQuestionIdFromSession = Long.parseLong(nextQuestionIdSession.toString());
            getQuestAndSetAttributes(questId, req, resp, nextQuestionIdFromSession);
        } else {
            questSubstitutionParams(req, resp, questId);
        }
    }

    private void playQuestAgain(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Long questId,
                                String questIdAttr, String nextQuestionIdAttr) throws IOException {
        session.removeAttribute(questIdAttr);
        session.removeAttribute(nextQuestionIdAttr);
        Go.redirect(req, resp, Page.QUEST_SERVLET + "?" + ID + "=" + questId);
    }

    private void getQuestAndSetAttributes(Long questId, HttpServletRequest req,
                                          HttpServletResponse resp,
                                          long nextQuestionId) throws ServletException, IOException {
        Quest quest = questService.getEntitiesMap().get(questId);
        HttpSession session = req.getSession();

        if (quest != null) {
            Question question = quest.getQuestion().get(nextQuestionId);
            if (question != null) {
                if (question.getQuestState() == QuestState.PLAYING) {
                    req.setAttribute(Attribute.PLAY_QUEST_NOW, question);
                } else {
                    req.removeAttribute(Attribute.PLAY_QUEST_NOW);
                    req.setAttribute(Attribute.PLAY_QUEST_FINISH, question);
                    req.setAttribute(Attribute.QUEST, quest);
                }
                Go.forward(req, resp, Page.QUEST_JSP);
            } else {
                session.invalidate(); // if there was a DB change or the user changed the question id.
                questionSubstitutionParams(req, resp, nextQuestionId);
            }
        } else {
            questSubstitutionParams(req, resp, questId);
        }
    }

    private void updateUserPlayedQuestsAndQuestViews(HttpSession session, Long questId) {
        User userSession = (User) session.getAttribute(Attribute.USER);
        Quest quest = questService.getEntitiesMap().get(questId);
        if (quest != null) {
            UserService userService = UserService.INSTANCE;

            userService.updatePlayedQuestsCount(userSession);
            log.info(UPDATED_PLAYED_QUEST_COUNT, userSession.getId(), questId);

            questService.updateViews(quest);
            log.info(UPDATED_VIEWS_QUEST, questId, userSession.getId());
        }
    }

}