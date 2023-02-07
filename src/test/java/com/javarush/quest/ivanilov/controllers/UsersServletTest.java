package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.users.Role;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.Transfer;
import com.javarush.quest.ivanilov.utils.transfers.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UsersServletTest {

    @Test
    protected void doGet() {
        UserService userService = Mockito.mock(UserService.class);
        Game game = Mockito.mock(Game.class);
        Mockito.doReturn(1L).when(game).getId();
        List<Long> gamesPlayed = new ArrayList<>();
        gamesPlayed.add(game.getId());

        User user = User.builder()
                .login("user")
                .password("pass")
                .currentGameId(0)
                .gamesPlayed(gamesPlayed)
                .role(Role.USER).build();

        user.setId(1);
        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.doReturn(users).when(userService).getAll();

        Collection<User> usersFromUserService = userService.getAll();
        AbstractList<UserDto> userDtos = (AbstractList<UserDto>) new Transfer().extractUsersToSend(usersFromUserService);
        UserDto userDto = userDtos.get(0);
        UserDto userDtoSpy = Mockito.spy(userDto);

        Assertions.assertEquals(user.getLogin(), userDtoSpy.getLogin());
        Assertions.assertEquals(user.getId(), userDtoSpy.getId());
        Assertions.assertEquals("Не играет.", userDtoSpy.getCurrentGameName());
        Assertions.assertEquals(user.getRole(), userDtoSpy.getRole());
        Assertions.assertEquals(1, userDtoSpy.getGamesPlayed());
        Assertions.assertEquals(0, userDtoSpy.getGamesWon());
    }
}