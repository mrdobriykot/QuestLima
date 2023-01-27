package com.javarush.alimin.util;

import com.javarush.alimin.exception.AppException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class Forward {
    public void forward(HttpServletRequest request, HttpServletResponse response, String target) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(target);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new AppException(e);
        }
    }
}
