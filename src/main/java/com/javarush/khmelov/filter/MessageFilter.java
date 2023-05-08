package com.javarush.khmelov.filter;

import com.javarush.khmelov.controller.Key;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class MessageFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);

        HttpSession session = req.getSession(false);
        if (req.getMethod().equalsIgnoreCase("get") && Objects.nonNull(session)) {
            session.removeAttribute(Key.ERROR_MESSAGE);
        }
    }
}
