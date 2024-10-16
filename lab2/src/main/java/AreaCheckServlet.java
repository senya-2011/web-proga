import logic.HideServlet;
import logic.Point;
import logic.RequestManagers.RequestReader;
import logic.ResponseManagers.ContextManager;
import logic.ResponseManagers.ResponseSender;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {
    ContextManager contextManager = new ContextManager();
    ResponseSender responseSender = new ResponseSender();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        float[] params = (float[]) req.getAttribute("params");;
        Point point = new Point(params);
        contextManager.setNewPoint(point, req);
        responseSender.sendResponse(req,resp, point);


//        List<Point> points = (List<Point>) req.getServletContext().getAttribute("results");
//        for(Point point1:points){
//            resp.getWriter().write(point1.toString());
//        }

    }


//    private boolean checkRef(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if(hideServlet.checkReferrer(req)){
//            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
//            return false;
//        }
//        return true;
//    }
}
