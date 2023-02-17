package com.javarush.quest.sternard.filter;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.util.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static com.javarush.quest.sternard.util.Attribute.PAGE_TITLE;


@WebFilter(filterName = "PageTitleFilter", urlPatterns = Page.ALL)
public class PageTitleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        String requestURI = req.getRequestURI();
        String uriLastPathSegment = requestURI.substring(requestURI.lastIndexOf('/'));

        Map<String, String> pageTitleMap = Settings.get().getPageTitleMap();
        String pageTitleDefault = Settings.get().getPageTitleDefault();

        String pageTitle = pageTitleMap.getOrDefault(uriLastPathSegment, pageTitleDefault);
        request.setAttribute(PAGE_TITLE, pageTitle);

        chain.doFilter(req, response);
    }
}