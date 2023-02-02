package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.users.Roles;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignupServletTest {

    @Test
    protected void doPost() {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        UserService userService = Mockito.mock(UserService.class);
        User user = Mockito.mock(User.class);

        Mockito.doReturn("anyLogin").when(req).getParameter(Attributes.LOGIN);
        Mockito.doReturn("anyPassword").when(req).getParameter(Attributes.PASSWORD);
        Mockito.doReturn(user).when(userService).createOrModifyUser("anyLogin", "anyPassword", Roles.USER, null);

        String login = req.getParameter(Attributes.LOGIN);
        String password = req.getParameter(Attributes.PASSWORD);
        User testUser = userService.createOrModifyUser(login, password, Roles.USER, null);
        Assertions.assertNotNull(testUser);
    }

}