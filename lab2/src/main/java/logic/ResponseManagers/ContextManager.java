package logic.ResponseManagers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.Point;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import static com.sun.jmx.mbeanserver.Util.cast;

public class ContextManager {
    public void setNewPoint(Point point, HttpServletRequest req){
        HttpSession session = req.getSession();

        String id = (String) session.getAttribute("id");
        if (id == null) {
            id = UUID.randomUUID().toString();
            session.setAttribute("id", id);
        }
        point.setId(id);
        ServletContext context = req.getServletContext();
        List<Point> results;
        Object resultObj= context.getAttribute("results");
        if (resultObj instanceof List<?>){
            results = (List<Point>) (resultObj);
        }else{
            results = new ArrayList<>();
        }
        session.setAttribute("results", results.toString());
        results.add(0, point);
        context.setAttribute("results", results);
    }
}
