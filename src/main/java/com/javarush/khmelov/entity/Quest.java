package com.javarush.khmelov.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Quest implements AbstractEntity {
    public static final String LAZY_QUESTIONS_AND_JOIN_AUTHOR = "lazy_questions_and_join_author";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @ToString.Exclude
    private User author;

    private Long startQuestionId;

    @OneToMany(targetEntity = Question.class)
    @JoinColumn(name = "quest_id")
    @ToString.Exclude
    private final List<Question> questions = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "game",
            joinColumns = @JoinColumn(name = "quest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private final Collection<User> players = new ArrayList<>();

}
