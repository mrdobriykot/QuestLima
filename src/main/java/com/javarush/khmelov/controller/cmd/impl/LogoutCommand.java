package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.view.View;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller(Go.LOGOUT)
@AllArgsConstructor
public class LogoutCommand implements Command {
    @Override
    public View get(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        return View.forward(Go.LOGIN);
    }
}
