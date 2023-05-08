package com.javarush.khmelov.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Objects;

import static com.javarush.khmelov.controller.Key.ERROR_MESSAGE;

@UtilityClass
public class Jsp {

    public void forward(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        target = fixTarget(target);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/%s.jsp".formatted(target));
        requestDispatcher.forward(request, response);
    }

    @SneakyThrows
    public void redirect(HttpServletResponse response, String target){
        target = fixTarget(target);
        response.sendRedirect(target);
    }

    private String fixTarget(String target) {
        target = target.replace("/","");
        return target;
    }
    public void forward(HttpServletRequest req, HttpServletResponse resp, String uri, String errorMessage)
            throws ServletException, IOException {
        req.getSession().setAttribute(ERROR_MESSAGE, errorMessage);
        forward(req, resp, uri);
    }

    public void redirect(HttpServletRequest req, HttpServletResponse resp, String uri, String errorMessage)
            throws IOException {
        req.getSession().setAttribute(ERROR_MESSAGE, errorMessage);
        resp.sendRedirect(req.getContextPath() + uri);
    }

    private void findErrorInSessionAfterForwardOrRedirect(HttpServletRequest req) {
        if (Objects.nonNull(req.getSession(false))) {
            HttpSession session = req.getSession();
            Object message = session.getAttribute(ERROR_MESSAGE);
            if (Objects.nonNull(message)) {
                session.removeAttribute(ERROR_MESSAGE);
                req.setAttribute(ERROR_MESSAGE, message);
            }
        }
    }
}
