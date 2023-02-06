package com.javarush.quest.ivanilov.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.javarush.quest.ivanilov.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@JsonDeserialize(builder = User.UserBuilder.class)
@JsonIgnoreProperties({"id"})
public class User extends AbstractEntity implements Serializable {

    private String login;

    @ToString.Exclude
    private String password;
    private Role role;

    @ToString.Exclude
    private long currentGameId;

    @ToString.Exclude
    private List<Long> gamesPlayed;

    User(String login, String password, Role role, long currentGameId, List<Long> gamesPlayed) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.currentGameId = currentGameId;
        this.gamesPlayed = gamesPlayed;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserBuilder {
        private String login;
        private String password;
        private Role role;
        private long currentGameId;
        private List<Long> gamesPlayed;

        UserBuilder() {
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder currentGameId(long currentGameId) {
            this.currentGameId = currentGameId;
            return this;
        }

        public UserBuilder gamesPlayed(List<Long> gamesPlayed) {
            this.gamesPlayed = gamesPlayed;
            return this;
        }

        public User build() {
            return new User(login, password, role, currentGameId, gamesPlayed);
        }

        public String toString() {
            return "User.UserBuilder(login=" + this.login + ", password=" + this.password + ", role=" + this.role + ", currentGameId=" + this.currentGameId + ", gamesPlayed=" + this.gamesPlayed + ")";
        }
    }
}
