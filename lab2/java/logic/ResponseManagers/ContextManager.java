package logic.ResponseManagers;

import logic.Point;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ContextManager {
    public void setNewPoint(Point point, HttpServletRequest req){
        ServletContext context = req.getServletContext();
        List<Point> results = (List<Point>) context.getAttribute("results");
        if (results == null) {
            results = new ArrayList<>();
        }
        results.add(0, point);
        context.setAttribute("results", results);
    }
}
