package com.javarush.quest.osypenko.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DB implements Serializable {
    private Long id;
    private String question;
    private String answer;
    private HashMap<String, String> url;
    private String[] images;
}
