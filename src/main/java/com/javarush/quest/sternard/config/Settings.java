package com.javarush.quest.sternard.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.javarush.quest.sternard.repository.ChooserQuestsRepository;
import com.javarush.quest.sternard.repository.ChooserUsersRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.javarush.quest.sternard.config.Default.SETTINGS_JSON;
import static com.javarush.quest.sternard.util.message.LoggerMessages.ERROR_UPDATING_SETTINGS_FROM_JSON;

@Data
@Slf4j
@JsonIgnoreProperties(value = {"usersRepositoryToLoad", "questsRepositoryToLoad"})
public class Settings {
    private static volatile Settings SETTINGS;
    private ChooserUsersRepository usersRepositoryToLoad;
    private ChooserQuestsRepository questsRepositoryToLoad;
    private String resourcesFolder;
    private String usersJsonFilename;
    private String questsJsonFilename;
    private double minQuestRating;
    private double maxQuestRating;
    private double upDownUnitQuestRating;
    private String imgDefaultAvatarsPath;
    private String defaultAvatarImage;
    private String hikariConfig;
    private String hibernateConfig;
    private int usersRecordsPerPagePaginator;
    private int questsRecordsPerPagePaginator;
    private long statBestQuestLimit;
    private long statMostPlayingUsersLimit;
    private String[] extensionImages;
    private String pageTitleDefault;
    private Map<String, String> pageTitleMap;
    private Map<String, String> imagesSubFolders;
    private String imagesFolder;
    private Set<String> allowedPagesForGuest;
    private Set<String> allowedPagesForUser;
    private Set<String> allowedPagesForEditor;
    private Set<String> allowedPagesForAdmin;

    public static Settings get() {
        Settings settings = SETTINGS;
        if (Objects.isNull(settings)) {
            synchronized (Settings.class) {
                if (Objects.isNull(settings = SETTINGS)) {
                    settings = SETTINGS = new Settings();
                }
            }
        }
        return settings;
    }

    private Settings() {
        loadDefaultSettings();
        updateSettingsFromJson();
    }

    private void loadDefaultSettings() {
        this.usersRepositoryToLoad = Default.USERS_REPOSITORY_TO_LOAD;
        this.questsRepositoryToLoad = Default.QUESTS_REPOSITORY_TO_LOAD;
        this.resourcesFolder = Default.RESOURCES_FOLDER;
        this.usersJsonFilename = Default.USERS_JSON_FILENAME;
        this.questsJsonFilename = Default.QUESTS_JSON_FILENAME;
        this.minQuestRating = Default.MIN_QUEST_RATING;
        this.maxQuestRating = Default.MAX_QUEST_RATING;
        this.upDownUnitQuestRating = Default.UP_DOWN_UNIT_QUEST_RATING;
        this.imgDefaultAvatarsPath = Default.IMG_DEFAULT_AVATARS_PATH;
        this.defaultAvatarImage = Default.DEFAULT_AVATAR_IMAGE;
        this.hikariConfig = Default.HIKARI_CONFIG;
        this.hibernateConfig = Default.HIBERNATE_CONFIG;
        this.usersRecordsPerPagePaginator = Default.USERS_RECORDS_PER_PAGE_PAGINATOR;
        this.questsRecordsPerPagePaginator = Default.QUESTS_RECORDS_PER_PAGE_PAGINATOR;
        this.extensionImages = Default.EXTENSION_IMAGES;
        this.pageTitleDefault = Default.PAGE_TITLE_DEFAULT;
        this.pageTitleMap = Default.PAGE_TITLE_MAP;
        this.statBestQuestLimit = Default.STAT_BEST_QUEST_LIMIT;
        this.statMostPlayingUsersLimit = Default.STAT_MOST_PLAYING_USERS_LIMIT;
        this.imagesSubFolders = Default.IMAGES_SUBFOLDERS;
        this.imagesFolder = Default.IMAGES_FOLDER;
        this.allowedPagesForGuest = Default.ALLOWED_PAGES_FOR_GUEST;
        this.allowedPagesForUser = Default.ALLOWED_PAGES_FOR_USER;
        this.allowedPagesForEditor = Default.ALLOWED_PAGES_FOR_EDITOR;
        this.allowedPagesForAdmin = Default.ALLOWED_PAGES_FOR_ADMIN;
    }

    private void updateSettingsFromJson() {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        ObjectReader readerForUpdating = mapper.readerForUpdating(this);
        URL resource = Settings.class
                .getClassLoader()
                .getResource(SETTINGS_JSON);

        if (Objects.nonNull(resource)) {
            try (InputStream src = resource.openStream()) {
                readerForUpdating.readValue(src);
            } catch (IOException e) {
                log.error(ERROR_UPDATING_SETTINGS_FROM_JSON, e.getMessage());
            }
        }
    }

    public String getUsersJsonFilename() {
        return resourcesFolder + usersJsonFilename;
    }

    public String getQuestsJsonFilename() {
        return resourcesFolder + questsJsonFilename;
    }

    public String getHikariConfig() {
        return resourcesFolder + hikariConfig;
    }

    public String getHibernateConfig() {
        return resourcesFolder + hibernateConfig;
    }
}
