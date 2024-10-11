package logic.ResponseManagers;


import logic.Point;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseSender {

    public void sendResponse(HttpServletResponse resp, Point point) throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String response = String.format("{\"x\":%f,\"y\":%f,\"r\":%f,\"status\":\"%s\"}",
                point.getX(), point.getY(), point.getR(), point.getStatus());
        resp.getWriter().write(response);
    }
}
