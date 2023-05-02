package com.javarush.mokropolov.util;

import com.javarush.mokropolov.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

import java.util.Optional;
@UtilityClass 
public class Parser {

    public Long getId(HttpServletRequest req) {
        return getId(req, Key.ID);
    }

    public Long getId(HttpServletRequest req, String key) {
        String id = req.getParameter(key);
        return id != null && !id.isBlank()
                ? Long.parseLong(id)
                : 0L;
    }

    public Long getId(HttpSession session) {
        Object user = session.getAttribute(Key.USER);
        return user != null
                ? ((User) user).getId()
                : 0L;
    }

    public Optional<User> getUser(HttpSession session) {
        Object user = session.getAttribute(Key.USER);
        return user != null
                ? Optional.of((User) user)
                : Optional.empty();
    }
}
