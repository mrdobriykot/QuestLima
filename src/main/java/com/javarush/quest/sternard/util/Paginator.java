package com.javarush.quest.sternard.util;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class Paginator {
    public static <T> List<T> getEntitiesOnPage(HttpServletRequest request, Collection<T> entities,
                                                String pageNum, int recordsPerPage) {
        double rows = entities.size();
        int currentPage = (pageNum == null) ? 1 : Integer.parseInt(pageNum);
        if (currentPage == 0) currentPage = 1;
        int pagesCount = (int) Math.ceil(rows / recordsPerPage);

        int startPageCalculation = (currentPage * recordsPerPage) - recordsPerPage;
        int start = startPageCalculation > rows ? 0 : startPageCalculation;

        int endPageCalculation = start + recordsPerPage;
        int end = endPageCalculation > rows ? (int) rows : endPageCalculation;

        request.setAttribute(Attribute.PAGES_COUNT, pagesCount);
        request.setAttribute(Attribute.CURRENT_PAGE, currentPage);

        return entities.stream()
                .toList()
                .subList(start, end);
    }
}
