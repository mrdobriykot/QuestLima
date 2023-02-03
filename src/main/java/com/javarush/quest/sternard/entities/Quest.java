package com.javarush.quest.sternard.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Quest extends AbstractEntity {
    private long id;
    private String title;
    private String description;
    private String image;
    private LocalDate creationDate;
    private int views;
    private double rating;
    private String author;
    private int firstQuestionId;
    private Map<Long, Question> question;
}
