package com.javarush.quest.ivanilov.services;

import com.javarush.quest.ivanilov.constants.Strings;
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

    private List<String[]> parseLines(String text) {
        List<String[]> lines = new ArrayList<>();
        String[] rows = text.split(";");

        for (String row : rows) {
            String[] line = row.split(":");
            line[0] = line[0].replace("\n", "");
            lines.add(line);
        }

        return lines;
    }

    private Map<String, Event> initEvents(List<String[]> lines, long questId) {
        Map<String, Event> events = new HashMap<>();

        for (String[] line : lines) {
            if(line.length < 2) {
                continue;
            }

            String param = line[0].replace("\n", "");
            String description = line[1];

            if (param.contains("Event")) {
                Event event = Event.builder()
                        .name(description)
                        .questId(questId)
                        .build();

                events.put(param, eventService.create(event));
            }
        }
        return events;
    }

    private Quest parseQuest(List<String[]> lines) {
        for (String[] line : lines) {
            String param = line[0];
            String name = line[1];

            if (param.contains(Strings.QUEST)) {
                Quest newQuest = Quest.builder()
                        .name(name)
                        .build();
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
                Map<String, String> attributesMap = parseAttributes(attributes);
                String name = attributesMap.getOrDefault(Strings.NAME, null);
                String strength = attributesMap.getOrDefault(Strings.STRENGTH, String.valueOf(5));
                String health = attributesMap.getOrDefault(Strings.HEALTH, String.valueOf(40));

                Hero hero = Hero.builder()
                        .name(name)
                        .health(Integer.parseInt(health))
                        .strength(Integer.parseInt(strength))
                        .build();

                return heroService.create(hero);
            }
        }
        throw new IllegalArgumentException(Strings.HERO_NOT_FOUND);
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

    public void initQuest(String text) {
        List<String[]> lines = parseLines(text);
        Quest newQuest = parseQuest(lines);
        Hero hero = parseHero(lines);
        newQuest.setHero(hero);
        Map<String, Event> events = initEvents(lines, newQuest.getId());
        newQuest.setCurrentEventId(events.get(Strings.EVENT_1).getId());
        fillEventsWithParams(events, lines);
        questService.update(newQuest);
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
            String string = line[1];

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
                    curr.setEventType(EventType.TASK);
                    Task task = Task.builder()
                            .type(TaskType.QUESTION)
                            .description(string)
                            .Options(new HashMap<>())
                            .Results(new HashMap<>())
                            .build();
                    Task question = taskService.create(task);
                    curr.setEventType(EventType.TASK);
                    curr.setTask(question);
                }
                case Strings.ANSWER -> {
                    Task question = curr.getTask();
                    String[] answer = string.split("\\^");
                    String text = answer[0];
                    String target = answer[1];
                    question.getOptions().put(answerCounter, text);
                    question.getResults().put(answerCounter, events.get(target));
                    answerCounter++;
                }
                case Strings.WIN -> {
                    curr.setEventType(EventType.WIN);
                    curr.setMessage(string);
                }
                case Strings.LOSE -> {
                    curr.setEventType(EventType.LOSE);
                    curr.setMessage(string);
                }
                case Strings.FIGHT -> {
                    Task task = Task.builder()
                            .description(string)
                            .type(TaskType.FIGHT)
                            .build();
                    Task fight = taskService.create(task);
                    curr.setTask(fight);
                    curr.setEventType(EventType.TASK);
                }
                case Strings.VILLAIN -> {
                    Map<String, String> attributesMap = parseAttributes(string);
                    String name = attributesMap.getOrDefault(Strings.NAME, null);
                    String strength = attributesMap.getOrDefault(Strings.STRENGTH, String.valueOf(5));
                    String health = attributesMap.getOrDefault(Strings.HEALTH, String.valueOf(40));

                    Hero hero = Hero.builder()
                            .name(name)
                            .health(Integer.parseInt(health))
                            .strength(Integer.parseInt(strength))
                            .build();

                    Hero villain = heroService.create(hero);
                    curr.getTask().setVillain(villain);
                }
                case Strings.GOOD -> curr.getTask().setNextEventIfFightWasWon(events.get(string));
                case Strings.BAD -> curr.getTask().setNextEventIfFightWasLost(events.get(string));

                default -> throw new IllegalStateException(Strings.UNEXPECTED_VALUE + param);
            }
        }
    }
}
