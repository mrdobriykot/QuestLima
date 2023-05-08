package com.javarush.khmelov.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class View {

    public enum Action {FORWARD, REDIRECT}

    private final String uri;
    private final Action action;
    private final String errorMessage;

    public static View forward(String uri) {
        return forward(uri, null);
    }

    public static View forward(String uri, String errorMessage) {
        return new View(uri, Action.FORWARD, errorMessage);
    }

    public static View redirect(String uri) {
        return redirect(uri, null);
    }

    public static View redirect(String uri, String errorMessage) {
        return new View(uri, Action.REDIRECT, null);
    }

    private View(String uri, Action action, String errorMessage) {
        this.uri = uri;
        this.action = action;
        this.errorMessage = errorMessage;
    }

    public void goToView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (action == View.Action.FORWARD) {
            if (errorMessage == null) {
                Jsp.forward(req, resp, uri);
            } else {
                Jsp.forward(req, resp, uri, errorMessage);
            }
        } else {
            if (errorMessage == null) {
                Jsp.redirect(resp, uri);
            } else {
                Jsp.redirect(req, resp, uri, errorMessage);
            }
        }
    }
}
