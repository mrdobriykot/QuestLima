package com.javarush.quest.sternard.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.sternard.config.Settings;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.javarush.quest.sternard.util.message.LoggerMessages.*;
import static java.nio.file.Paths.get;

@Slf4j
@UtilityClass
public class CommonCode {
    public static Map<String, String> checkedParamsFromJson(HttpServletRequest req, List<String> requiredParams)
            throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(inputStream);
        Map<String, String[]> params = mapper.convertValue(tree, new TypeReference<>() {
        });
        return Validator.checkAndReturnMap(params, requiredParams);
    }

    public static Map<String, String> getCheckedParams(HttpServletRequest req, List<String> requiredParams) {
        Map<String, String[]> params = req.getParameterMap();
        return Validator.checkAndReturnMap(params, requiredParams);
    }

    public static List<String> getAvatarImagesList(ServletContextEvent sce) {
        String imgDefaultAvatarsPath = Settings.get().getImgDefaultAvatarsPath();
        String[] extensionsAvatarsImage = Settings.get().getExtensionImages();

        ServletContext servletContext = sce.getServletContext();
        String imagesPath = servletContext.getRealPath(imgDefaultAvatarsPath);
        File avatarsDirectory = get(imagesPath).toFile();
        List<File> images = (List<File>) FileUtils.listFiles(avatarsDirectory, extensionsAvatarsImage, true);
        return images.stream()
                .map(File::getName)
                .toList();
    }

    public static void questSubstitutionParams(HttpServletRequest req, HttpServletResponse resp, Long id) throws IOException {
        log.warn(QUEST_SUBSTITUTION_PARAMETERS, id);
        Go.redirect(req, resp, Page.QUESTS_SERVLET);
    }

    public static void userSubstitutionParams(HttpServletRequest req, HttpServletResponse resp, Map<String, String[]> params)
            throws IOException {
        log.warn(USER_SUBSTITUTION_PARAMETERS, params);
        Go.redirect(req, resp, Page.HOME_SERVLET);
    }

    public static void questionSubstitutionParams(HttpServletRequest req, HttpServletResponse resp, Long id) throws IOException {
        log.warn(QUESTION_SUBSTITUTION_PARAMETERS, id);
        Go.redirect(req, resp, Page.QUESTS_SERVLET);
    }

    public static void badParams(HttpServletRequest req, HttpServletResponse resp, Map<String, String[]> params, String redirectToPage)
            throws IOException {
        log.warn(BAD_PARAMETERS, params);
        Go.redirect(req, resp, redirectToPage);
    }

}