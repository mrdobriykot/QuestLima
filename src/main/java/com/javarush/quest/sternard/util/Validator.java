package com.javarush.quest.sternard.util;

import com.javarush.quest.sternard.config.Settings;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.javarush.quest.sternard.util.Parameter.RATING_DOWN;
import static com.javarush.quest.sternard.util.Parameter.RATING_UP;

@UtilityClass
public class Validator {
    private static final Map<String, String> paramsRegExp = new HashMap<>();
    public static final String EXT_IMAGES_JOINED = String.join("|", Settings.get().getExtensionImages());

    static {
        // Parameters and allowed symbols
        paramsRegExp.put(Parameter.ID, "^[0-9]{1,15}$");
        paramsRegExp.put(Parameter.NEXT_QUESTION_ID, "^[0-9]{1,15}$");
        paramsRegExp.put(Parameter.PLAY_QUEST_AGAIN_ID, "^[0-9]{1,15}$");
        paramsRegExp.put(Parameter.RATING_UP_DOWN, "^(" + RATING_UP + "|" + RATING_DOWN + ")$");
        paramsRegExp.put(Parameter.PAGE_NUMBER, "^[0-9]{1,8}$");
        paramsRegExp.put(Parameter.LOGIN, "^[a-zA-ZА-Яа-яЁё0-9_-]{2,30}$");
        paramsRegExp.put(Parameter.PASSWORD, "^[a-zA-ZА-Яа-яЁё0-9_-]{0,30}$");
        paramsRegExp.put(Parameter.PASSWORD_REG, "^[a-zA-ZА-Яа-яЁё0-9_-]{2,30}$");
        paramsRegExp.put(Parameter.ROLE, "^[a-zA-Z]{3,10}$");
        paramsRegExp.put(Parameter.IMAGE, "^([a-zA-Z0-9_-]+)\\.(" + EXT_IMAGES_JOINED + "){1}$");
        paramsRegExp.put(Parameter.TITLE, "^[a-zA-Z_\sА-Я\"а-яЁё?!,?:;{}\\[\\]'`=%«»\\.0-9-]+$");
        paramsRegExp.put(Parameter.DESCRIPTION, "^[a-zA-Z_\sА-Я\"а-яЁё?!,:;{}\\[\\]'`=%«»\\.0-9-]+$");
        paramsRegExp.put(Parameter.CREATION_DATE, "^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
        paramsRegExp.put(Parameter.VIEWS, "^[0-9]{1,8}$");
        paramsRegExp.put(Parameter.AUTHOR, "^[a-zA-ZА-Яа-яЁё0-9_-]+$");
        paramsRegExp.put(Parameter.RATING, "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$");// Not valid: 05 | 5. | .5 | 0,5
    }

    public static Map<String, String> checkAndReturnMap(Map<String, String[]> parametersMap, List<String> requiredParams) {
        if (!checkRequiredParams(parametersMap, requiredParams)) return null;

        Map<String, String> checkedParamsMap = new HashMap<>();

        for (Map.Entry<String, String[]> entry : parametersMap.entrySet()) {
            String paramKey = entry.getKey();
            String[] paramValues = entry.getValue();
            if (checkByEmptyNullExistKeyMap(paramKey, paramValues)) {
                for (String val : paramValues) {
                    String regex = paramsRegExp.get(paramKey);
                    boolean matches = val.matches(regex);
                    if (!matches) {
                        return null; // bad ?
                    } else {
                        checkedParamsMap.put(paramKey, val);
                    }
                }
            } else
                return null; // bad ?
        }
        return checkedParamsMap;
    }

    private static boolean checkByEmptyNullExistKeyMap(String paramKey, String[] paramValues) {
        return ObjectUtils.isNotEmpty(paramKey.trim())
                && ObjectUtils.isNotEmpty(paramValues)
                && paramsRegExp.containsKey(paramKey);
    }

    /**
     * Example:
     * Need parameters: id, login, password
     * But we have only: id, password
     * CollectionUtils.subtract... = login and size() > 0
     * then return false
     */
    private static boolean checkRequiredParams(Map<String, String[]> parametersMap, List<String> requiredParams) {
        Set<String> keySet = parametersMap.keySet();
        return CollectionUtils.subtract(requiredParams, keySet).size() == 0;
    }

}
