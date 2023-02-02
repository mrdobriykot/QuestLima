package com.javarush.quest.uzienko.questlima.config;

public final class UrlConfig {
    private UrlConfig() {
    }

    public static final String ENDPOINT_QUEST_MAIN_PAGE = "/quest";
    public static final String ENDPOINT_QUESTION_PAGE = "/question";
    public static final String ENDPOINT_ERROR_404 = "/error404";
    public static final String VIEW_FOR_HELLO_SERVLET = "welcome-include";
    public static final String VIEW_FOR_QUEST_MAIN_PAGE_SERVLET = "quest-main-include";
    public static final String VIEW_FOR_QUESTION_PLAY_PAGE_SERVLET = "question-play-include";
    public static final String VIEW_FOR_QUESTION_LOST_PAGE_SERVLET = "question-lose-include";
    public static final String VIEW_FOR_QUESTION_WIN_PAGE_SERVLET = "question-win-include";
}
