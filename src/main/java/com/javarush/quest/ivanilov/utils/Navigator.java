package com.javarush.quest.ivanilov.utils;

import com.javarush.quest.ivanilov.exceptions.QuestLimaException;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Navigator {
    public static void dispatch(HttpServletRequest req, HttpServletResponse resp, String target) {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher(target);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new QuestLimaException(e);
        }
    }

    public static void redirect(HttpServletRequest req, HttpServletResponse resp, String target) {
        try {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + target);
        } catch (Exception e) {
            throw new QuestLimaException(e);
        }
    }

    public static void redirectError(HttpServletRequest req, HttpServletResponse resp, String errorMessage, Throwable throwable) {
        Throwable exception = new QuestLimaException(errorMessage, throwable);
        req.getSession().setAttribute(Attributes.ERROR, exception);
        Navigator.redirect(req, resp, Targets.ERROR);
    }

    public static void redirectError(HttpServletRequest req, HttpServletResponse resp, String errorMessage) {
        Throwable exception = new QuestLimaException(errorMessage);
        req.getSession().setAttribute(Attributes.ERROR, exception);
        Navigator.redirect(req, resp, Targets.ERROR);
    }
}
