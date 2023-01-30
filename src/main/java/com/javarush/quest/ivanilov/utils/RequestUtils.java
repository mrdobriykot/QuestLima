package com.javarush.quest.ivanilov.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    public static Map<String, String> getAttributes(HttpServletRequest req) throws IOException {
        InputStreamReader isr = new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return parseAttributes(reader.readLine());

    }

    public static Map<String, String> getAttributes(String queryString) {
        return parseAttributes(queryString);
    }

    private static Map<String, String> parseAttributes(String body) {
        Map<String, String> attributes = new HashMap<>();
        String[] parameters = body.split("&");

        for (String attribute : parameters) {
            String[] nameAndValue = attribute.split("=");
            if (nameAndValue.length <= 1) {
                continue;
            }
            attributes.put(nameAndValue[0], nameAndValue[1]);
        }

        return attributes;
    }
}
