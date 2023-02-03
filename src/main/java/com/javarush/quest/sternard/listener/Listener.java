package com.javarush.quest.sternard.listener;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.entities.Role;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.repository.ChooserQuestsRepository;
import com.javarush.quest.sternard.repository.ChooserUsersRepository;
import com.javarush.quest.sternard.repository.Repository;
import com.javarush.quest.sternard.util.Attribute;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

import static com.javarush.quest.sternard.util.CommonCode.getAvatarImagesList;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    public static Repository<User> userRepository;
    public static Repository<Quest> questRepository;
    private final ChooserQuestsRepository repositoryOfQuests = Settings.get().getQuestsRepositoryToLoad();
    private final ChooserUsersRepository repositoryOfUsers = Settings.get().getUsersRepositoryToLoad();

    public Listener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        List<String> imageList = getAvatarImagesList(sce);

        sce.getServletContext().setAttribute(Attribute.DEFAULT_AVATARS_IMAGES, imageList);
        sce.getServletContext().setAttribute(Attribute.ROLES, Role.values());

        userRepository = repositoryOfUsers.getRepository();
        questRepository = repositoryOfQuests.getRepository();
    }

}
