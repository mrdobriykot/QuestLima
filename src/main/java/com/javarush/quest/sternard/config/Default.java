package com.javarush.quest.sternard.config;

import com.javarush.quest.sternard.repository.ChooserQuestsRepository;
import com.javarush.quest.sternard.repository.ChooserUsersRepository;
import com.javarush.quest.sternard.service.ImageService;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.nio.file.Path;
import java.util.*;

import static com.javarush.quest.sternard.util.Page.*;

@UtilityClass
public class Default {
    public static final ChooserUsersRepository USERS_REPOSITORY_TO_LOAD = ChooserUsersRepository.JSON_REPOS;
    public static final ChooserQuestsRepository QUESTS_REPOSITORY_TO_LOAD = ChooserQuestsRepository.JSON_REPOS;
    public static final Map<String, String> IMAGES_SUBFOLDERS = new HashMap<>();
    public static final String PAGE_TITLE_DEFAULT = "Quests for JavaRush company";
    public static final Map<String, String> PAGE_TITLE_MAP = new HashMap<>();
    public static final Set<String> ALLOWED_PAGES_FOR_GUEST = new HashSet<>(Set.of(
            HOME_SERVLET,
            HOME_SERVLET_EMPTY,
            HOME_SERVLET_SLASH,
            QUESTS_SERVLET,
            USERS_SERVLET,
            LOGIN_SERVLET,
            REGISTRATION_SERVLET,
            STATISTIC_SERVLET,
            LOGOUT_SERVLET,
            PROFILE_SERVLET
    ));

    public static final Set<String> ALLOWED_PAGES_FOR_USER = new HashSet<>(Set.of(
            QUEST_SERVLET,
            EDIT_USER_SERVLET
    ));

    public static final Set<String> ALLOWED_PAGES_FOR_EDITOR = new HashSet<>(Set.of(
            DELETE_QUEST_SERVLET,
            EDIT_QUEST_SERVLET
    ));

    public static final Set<String> ALLOWED_PAGES_FOR_ADMIN = new HashSet<>(Set.of(
            DELETE_USER_SERVLET,
            EDIT_SETTINGS_SERVLET
    ));

    public static final String[] EXTENSION_IMAGES = {"jpeg", "jpg", "png", "gif", "bmp", "webp"};
    public static final double MIN_QUEST_RATING = -10000.0;
    public static final double MAX_QUEST_RATING = 10000.0;
    public static final double UP_DOWN_UNIT_QUEST_RATING = 1.0;
    public static final int USERS_RECORDS_PER_PAGE_PAGINATOR = 20;
    public static final int QUESTS_RECORDS_PER_PAGE_PAGINATOR = 1;
    public static final String RESOURCES_FOLDER = "resources/";
    public static final String SETTINGS_JSON = RESOURCES_FOLDER + "settings.json";
    public static final String HIKARI_CONFIG = RESOURCES_FOLDER + "hikari.properties";
    public static final String HIBERNATE_CONFIG = RESOURCES_FOLDER + "hibernate.cfg.xml";
    public static final String USERS_JSON_FILENAME = RESOURCES_FOLDER + "users.json";
    public static final String QUESTS_JSON_FILENAME = RESOURCES_FOLDER + "quests.json";
    public static final String IMG_DEFAULT_AVATARS_PATH = "/img/default/avatars/";
    public static final String DEFAULT_AVATAR_IMAGE = "avatar_no_image.jpg";
    public static final long STAT_BEST_QUEST_LIMIT = 5;
    public static final long STAT_MOST_PLAYING_USERS_LIMIT = 5;
    public static final String IMAGES_FOLDER = "img";
    public static final Path WEB_INF = Path.of(
                    URI.create(
                            Objects.requireNonNull(
                                    ImageService.class.getResource("/")).toString()
                    )
            ).getParent() // web-inf directory
            .getParent(); // root directory like as "quest-1.0-SNAPSHOT/"

    static {
        PAGE_TITLE_MAP.put(HOME_SERVLET, "Проект 3 модуля на JavaRush");
        PAGE_TITLE_MAP.put(HOME_SERVLET_EMPTY, "Проект 3 модуля на JavaRush");
        PAGE_TITLE_MAP.put(USERS_SERVLET, "Все пользователи");
        PAGE_TITLE_MAP.put(LOGIN_SERVLET, "Войти");
        PAGE_TITLE_MAP.put(QUESTS_SERVLET, "Все квесты");
        PAGE_TITLE_MAP.put(PROFILE_SERVLET, "Профиль пользователя");
        PAGE_TITLE_MAP.put(REGISTRATION_SERVLET, "Регистрация");
        PAGE_TITLE_MAP.put(LOGOUT_SERVLET, "До новых встреч");
        PAGE_TITLE_MAP.put(QUEST_SERVLET, "Квест");
        PAGE_TITLE_MAP.put(DELETE_USER_SERVLET, "Удаление пользователя");
        PAGE_TITLE_MAP.put(EDIT_USER_SERVLET, "Редактирование пользователя");
        PAGE_TITLE_MAP.put(EDIT_QUEST_SERVLET, "Редактирование квеста");
        PAGE_TITLE_MAP.put(DELETE_QUEST_SERVLET, "Удаление квеста");
        PAGE_TITLE_MAP.put(EDIT_SETTINGS_SERVLET, "Редактирование настроек сайта");
        PAGE_TITLE_MAP.put(STATISTIC_SERVLET, "Статистика сайта");

        // folder "quest" in "img" folder
        IMAGES_SUBFOLDERS.put("quest", "quest");
    }
}
