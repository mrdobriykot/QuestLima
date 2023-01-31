package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.Fight;
import com.javarush.quest.ivanilov.entities.game.Option;
import jakarta.servlet.http.HttpSession;

public interface GameWorker {
    void initializeGame(long playerId, long questId);

    Option[] getOptionsToSend(Event event);

    Event validateAnswer(Event event, int optionId);

    Fight getFightToSend(HttpSession session, Event event);

    Fight fight(Fight currentFight, String hitOptions, String blockOptions);
}
