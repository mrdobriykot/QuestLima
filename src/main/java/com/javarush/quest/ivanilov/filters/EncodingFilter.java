package com.javarush.quest.ivanilov.filters;

import com.javarush.quest.ivanilov.constants.Attributes;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import lombok.SneakyThrows;

@WebFilter(
        initParams = @WebInitParam(name = "code", value = "UTF-8"),
        filterName = "EncodingFilter"
)
public class EncodingFilter implements Filter {
    private String code;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter(Attributes.CODE);
    }

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        String encoding = request.getCharacterEncoding();
        if (!code.equalsIgnoreCase(encoding)) {
            request.setCharacterEncoding(code);
        }

        encoding = response.getCharacterEncoding();
        if (!code.equalsIgnoreCase(encoding)) {
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
