package com.javarush.khmelov.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Quest implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String text;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @ToString.Exclude
    private User author;

    @Column(name = "start_question_id")
    private Long startQuestionId;

    @OneToMany
    @JoinColumn(name = "quest_id")
    @ToString.Exclude
    private final Collection<Question> questions = new ArrayList<>();


//    @ManyToMany
//    @JoinTable(name = "game",
//            joinColumns = @JoinColumn(name = "quest_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id")
//    )
//    @ToString.Exclude
//    final Collection<User> players=new ArrayList<>();

}
