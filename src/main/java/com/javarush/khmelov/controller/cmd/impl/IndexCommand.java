package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.Key;
import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.view.View;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller(value = Go.HOME)
@AllArgsConstructor
public class IndexCommand implements Command {

    private final QuestService questService;

    @Override
    public View get(HttpServletRequest req) throws ServletException, IOException {
        req.setAttribute(Key.QUESTS, questService.getAll());
        return View.forward(Key.INDEX);
    }
}
