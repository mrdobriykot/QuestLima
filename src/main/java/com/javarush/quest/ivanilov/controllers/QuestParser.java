package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Strings;
import com.javarush.quest.ivanilov.entities.game.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class QuestParser {
    private QuestService questService;
    private EventService eventService;
    private TaskService taskService;
    private HeroService heroService;

    public void parse(String text) {
        List<String[]> lines = parseLines(text);
        Quest newQuest = parseQuest(lines);
        Hero hero = parseHero(lines);
        newQuest.setHero(hero);
        Map<String, Event> events = initEvents(lines, newQuest.getId());
        newQuest.setCurrentEventId(events.get(Strings.EVENT_1).getId());
        fillEventsWithParams(events, lines);
        questService.update(newQuest);
    }

    private List<String[]> parseLines(String text) {
        List<String[]> lines = new ArrayList<>();
        StringBuilder sb = removeLineBreaks(text);
        String[] rows = sb.toString().split(";");

        for (String row : rows) {
            String[] line = row.split(":");
            lines.add(line);
        }

        return lines;
    }

    private StringBuilder removeLineBreaks(String text) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\r' || c == '\n') {
                continue;
            }
            sb.append(c);
        }
        return sb;
    }

    private Map<String, Event> initEvents(List<String[]> lines, long questId) {
        Map<String, Event> events = new HashMap<>();

        for (String[] line : lines) {
            if (line.length < 2) {
                continue;
            }

            String param = line[0];
            String description = line[1];

            if (param.contains(Attributes.EVENT)) {
                Event event = buildEvent(questId, description);
                events.put(param, eventService.create(event));
            }
        }
        return events;
    }

    private static Event buildEvent(long questId, String description) {
        return Event.builder()
                .name(description)
                .questId(questId)
                .build();
    }

    private Quest parseQuest(List<String[]> lines) {
        for (String[] line : lines) {
            String param = line[0];
            String name = line[1];

            if (param.contains(Strings.QUEST)) {
                Quest newQuest = Quest.builder().name(name).build();
                return questService.create(newQuest);
            }
        }
        throw new IllegalArgumentException(Strings.QUEST_NOT_FOUND);
    }

    private Hero parseHero(List<String[]> lines) {
        for (String[] line : lines) {
            String param = line[0];
            String attributes = line[1];

            if (param.contains(Strings.HERO)) {
                Hero hero = parseAttributesAndBuildHero(attributes);
                return heroService.create(hero);
            }
        }
        throw new IllegalArgumentException(Strings.HERO_NOT_FOUND);
    }

    private Hero parseAttributesAndBuildHero(String attributes) {
        Map<String, String> attributesMap = parseAttributes(attributes);
        String name = attributesMap.getOrDefault(Strings.NAME, null);
        String strength = attributesMap.getOrDefault(Strings.STRENGTH, String.valueOf(5));
        String health = attributesMap.getOrDefault(Strings.HEALTH, String.valueOf(40));
        return buildHero(name, strength, health);
    }

    private static Hero buildHero(String name, String strength, String health) {
        return Hero.builder()
                .name(name)
                .health(Integer.parseInt(health))
                .strength(Integer.parseInt(strength))
                .build();
    }

    private Map<String, String> parseAttributes(String text) {
        Map<String, String> map = new HashMap<>();
        String[] attributes = text.split("\\^");

        for (String attribute : attributes) {
            String[] s = attribute.split("=");
            String name = s[0];
            String value = s[1];
            map.put(name, value);
        }
        return map;
    }

    private void fillEventsWithParams(Map<String, Event> events, List<String[]> lines) {
        boolean isEvent = false;
        Event curr = null;
        int answerCounter = 1;

        for (String[] line : lines) {
            if (line.length < 2) {
                continue;
            }

            String param = line[0];
            String text = line[1];

            if (param.contains(Strings.EVENT)) {
                isEvent = true;
                curr = events.get(param);
                continue;
            }

            if (param.contains(Strings.BREAK)) {
                isEvent = false;
                curr = null;
                answerCounter = 1;
                continue;
            }

            if (!isEvent) {
                continue;
            }

            switch (param) {
                case Strings.QUESTION -> {
                    Task question = initQuestion(curr, text);
                    curr.setEventType(EventType.TASK);
                    curr.setTask(question);
                }
                case Strings.ANSWER -> {
                    addAnswer(events, curr, answerCounter, text);
                    answerCounter++;
                }
                case Strings.WIN -> {
                    curr.setEventType(EventType.WIN);
                    curr.setMessage(text);
                }
                case Strings.LOSE -> {
                    curr.setEventType(EventType.LOSE);
                    curr.setMessage(text);
                }
                case Strings.FIGHT -> {
                    Task fight = initFight(text);
                    curr.setTask(fight);
                    curr.setEventType(EventType.TASK);
                }
                case Strings.VILLAIN -> {
                    Hero hero = parseAttributesAndBuildHero(text);
                    Hero villain = heroService.create(hero);
                    curr.getTask().setVillain(villain);
                }
                case Strings.GOOD -> curr.getTask().setNextEventIfFightWasWon(events.get(text));
                case Strings.BAD -> curr.getTask().setNextEventIfFightWasLost(events.get(text));

                default -> throw new IllegalStateException(Strings.UNEXPECTED_VALUE + param);
            }
        }
    }

    private Task initFight(String description) {
        Task fight = Task.builder()
                .description(description)
                .type(TaskType.FIGHT)
                .build();
        return taskService.create(fight);
    }

    private static void addAnswer(Map<String, Event> events, Event currEvent, int answerCounter, String string) {
        Task question = currEvent.getTask();
        String[] answer = string.split("\\^");
        String text = answer[0];
        String target = answer[1];
        question.getOptions().put(answerCounter, text);
        question.getResults().put(answerCounter, events.get(target));
    }

    private Task initQuestion(Event currEvent, String string) {
        currEvent.setEventType(EventType.TASK);
        Task question = Task.builder()
                .type(TaskType.QUESTION)
                .description(string)
                .Options(new HashMap<>())
                .Results(new HashMap<>())
                .build();
        return taskService.create(question);
    }
}
