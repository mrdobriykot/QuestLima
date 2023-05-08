package com.javarush.khmelov.controller.cmd;

import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.view.View;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface Command {

    default View get(HttpServletRequest request) throws ServletException, IOException {
        return View.forward(Go.HOME);
    }

    default View post(HttpServletRequest request) throws ServletException, IOException {
        return View.redirect(Go.HOME);
    }
}
