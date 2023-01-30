package com.javarush.quest.ivanilov.utils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Navigator {
    public static void dispatch(HttpServletRequest req, HttpServletResponse resp, String target) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(target);
        dispatcher.forward(req, resp);
    }

    public static void redirect(HttpServletResponse resp, String target) throws IOException {
        resp.sendRedirect(target);
    }
}
