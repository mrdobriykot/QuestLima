package com.javarush.quest.sternard.filter;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.Role;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.util.Attribute;
import com.javarush.quest.sternard.util.Go;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.javarush.quest.sternard.util.Page.*;

@WebFilter(filterName = "UserSessionAndRoleFilter", urlPatterns = {
        HOME_SERVLET,
        HOME_SERVLET_EMPTY,
        HOME_SERVLET_SLASH,
        QUESTS_SERVLET,
        LOGIN_SERVLET,
        LOGOUT_SERVLET,
        REGISTRATION_SERVLET,
        STATISTIC_SERVLET,
        PROFILE_SERVLET,
        QUEST_SERVLET,
        USERS_SERVLET,
        DELETE_QUEST_SERVLET,
        EDIT_QUEST_SERVLET,
        EDIT_USER_SERVLET,
        DELETE_USER_SERVLET,
        EDIT_SETTINGS_SERVLET
})
public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        User sessionUser = (User) req.getSession().getAttribute(Attribute.USER);
        String requestURI = req.getRequestURI();
        String uriLastPathSegment = requestURI.substring(requestURI.lastIndexOf('/'));

        Map<Role, Set<String>> uriMap = getUriMapForAllRoles();
        Role role = (Objects.isNull(sessionUser)) ? Role.GUEST : sessionUser.getRole();

        if (uriMap.get(role).contains(uriLastPathSegment))
            chain.doFilter(req, response);
        else
            Go.redirect(req, (HttpServletResponse) response, LOGIN_SERVLET);
    }

    /**
     * Roles:
     * ADMIN: has the right to go to all pages on site
     * EDITOR: ... to (guest & user & editor) pages
     * USER: ... to (guest & user) pages
     * GUEST: ... to guest pages
     */
    private static Map<Role, Set<String>> getUriMapForAllRoles() {
        Set<String> allowedPagesForAdmin = Settings.get().getAllowedPagesForAdmin();
        Set<String> allowedPagesForEditor = Settings.get().getAllowedPagesForEditor();
        Set<String> allowedPagesForUser = Settings.get().getAllowedPagesForUser();
        Set<String> allowedPagesForGuest = Settings.get().getAllowedPagesForGuest();

        // add allowed pages to roles
        allowedPagesForAdmin.addAll(allowedPagesForEditor);
        allowedPagesForEditor.addAll(allowedPagesForUser);
        allowedPagesForUser.addAll(allowedPagesForGuest);

        return Map.of(
                Role.ADMIN, allowedPagesForAdmin,
                Role.EDITOR, allowedPagesForEditor,
                Role.USER, allowedPagesForUser,
                Role.GUEST, allowedPagesForGuest
        );
    }

}