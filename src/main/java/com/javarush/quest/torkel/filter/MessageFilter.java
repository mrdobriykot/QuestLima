package com.javarush.quest.torkel.filter;

import com.javarush.quest.torkel.util.Key;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
