package com.javarush.alimin.util;

import com.javarush.alimin.exception.AppException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@UtilityClass
public class Forward {

    private static final Logger log = LoggerFactory.getLogger(Forward.class);
    public void forward(HttpServletRequest request, HttpServletResponse response, String target) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(target);
        try {
            log.debug("Forwarding request to {}", target);
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            throw new AppException(e);
        }
    }
}
