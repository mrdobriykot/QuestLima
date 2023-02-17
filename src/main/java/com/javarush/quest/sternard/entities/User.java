package com.javarush.quest.sternard.entities;

import com.javarush.quest.sternard.util.converter.RoleConverter;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public final class User extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "played_quests_count")
    private int playedQuestsCount;

    @Column(name = "role_id")
    @Convert(converter = RoleConverter.class)
    private Role role;
}
