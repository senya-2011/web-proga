package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // Проверяем, есть ли пароль в сессии
        String password = (String) httpRequest.getSession().getAttribute("password");
        if (password == null || !password.equals("admin")) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/login.xhtml");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
