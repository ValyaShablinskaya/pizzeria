package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.filters;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
