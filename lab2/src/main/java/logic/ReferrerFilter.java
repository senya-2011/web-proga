package logic;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ReferrerFilter implements Filter {
    HideServlet hideServlet = new HideServlet();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (checkRef(req, resp)){
            chain.doFilter(req, resp);
        }
    }
    private boolean checkRef(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(hideServlet.checkReferrer(req)){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return false;
        }
        return true;
    }
}
