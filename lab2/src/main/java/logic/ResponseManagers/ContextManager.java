package logic.ResponseManagers;

import logic.Point;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.sun.jmx.mbeanserver.Util.cast;

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
            results = cast(resultObj);
        }else{
            results = new ArrayList<>();
        }
        session.setAttribute("results", results.toString());
        results.add(0, point);
        context.setAttribute("results", results);
    }
}
