package logic.ResponseManagers;


import logic.Point;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseSender {

    public void sendResponse(HttpServletRequest req, HttpServletResponse resp, Point point) throws IOException, ServletException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String response = String.format("{\"x\":%f,\"y\":%f,\"r\":%f,\"status\":\"%s\"}",
                point.getX(), point.getY(), point.getR(), point.getStatus());
        resp.getWriter().write(response);
        //req.getRequestDispatcher("results.jsp").forward(req, resp);
    }
}
