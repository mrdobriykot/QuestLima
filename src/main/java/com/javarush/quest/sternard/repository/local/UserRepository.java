package com.javarush.quest.sternard.repository.local;

import com.javarush.quest.sternard.entities.Role;
import com.javarush.quest.sternard.entities.User;

public class UserRepository extends BaseRepository<User> {
    public UserRepository() {
        this.defaultUsers();
    }

    private void defaultUsers() {
        this.create(User.builder()
                .login("Login1")
                .password("9dcefba6204adb4fefcdb5347d8baa2b7061dd9a7a2da981f6c307837668bbc0")
                .role(Role.ADMIN)
                .profileImage("5.png")
                .build());

        this.create(User.builder()
                .login("Login2")
                .password("958232c6d38eefc818e8a59ced81f75a32be46b4f08b58c22958864fc0870eb8")
                .role(Role.USER)
                .profileImage("1.png")
                .build());

        this.create(User.builder()
                .login("Login3")
                .password("2eb58ee89075cfde7e44c31b8bcff9b04264313edf048669227c831d07999085")
                .role(Role.USER)
                .profileImage("3.png")
                .build());

        this.create(User.builder()
                .login("Login4")
                .password("c132309ed2c590be0f1e16a3ed4c762db7864209f167b7f754117eb4c6bc848d")
                .role(Role.EDITOR)
                .profileImage("2.png")
                .build());

        this.create(User.builder()
                .login("Login5")
                .password("2eb58ee89075cfde7e44c31b8bcff9b04264313edf048669227c831d07999085")
                .role(Role.ADMIN)
                .profileImage("7.png")
                .build());
    }

}