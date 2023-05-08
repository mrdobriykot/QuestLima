package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.Key;
import com.javarush.khmelov.controller.Parser;
import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.view.View;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller(Go.CREATE)
@AllArgsConstructor
public class CreateQuestCommand implements Command {

    private final QuestService questService;

    @Override
    public View get(HttpServletRequest request) throws ServletException, IOException {
        return View.forward(Go.CREATE);
    }

    @Override
    public View post(HttpServletRequest request) throws ServletException, IOException {
        String name = request.getParameter(Key.NAME);
        String text = request.getParameter(Key.TEXT);
        Optional<UserTo> optionalUser = Parser.getUser(request.getSession());
        optionalUser.ifPresent(user -> questService.create(name, text, user.getId()));
        return View.redirect(Go.HOME);
    }
}
