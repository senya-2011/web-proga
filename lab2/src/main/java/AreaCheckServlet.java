import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Point;
import logic.ResponseManagers.ContextManager;
import logic.ResponseManagers.ResponseSender;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {
    ContextManager contextManager = new ContextManager();
    ResponseSender responseSender = new ResponseSender();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        float[] params = (float[]) req.getAttribute("params");
        Point point = new Point(params);
        contextManager.setNewPoint(point, req);
        responseSender.sendResponse(resp, point);


//        List<Point> points = (List<Point>) req.getServletContext().getAttribute("results");
//        for(Point point1:points){
//            resp.getWriter().write(point1.toString());
//        }
//    private boolean checkRef(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if(hideServlet.checkReferrer(req)){
//            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
//            return false;
//        }
//        return true;
//    }

    }



}
