import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.RequestManagers.ParamChecker;
import logic.RequestManagers.RequestReader;
import java.io.IOException;
import java.util.UUID;

public class ControllerServlet extends HttpServlet {
    RequestReader requestReader = new RequestReader();
    ParamChecker paramChecker = new ParamChecker();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float[] params = requestReader.readRequest(req);
        if (paramChecker.isParam(params)){
            String token = UUID.randomUUID().toString(); //чтобы клиент не мог получить AreaCheck
            req.getSession().setAttribute("token", token);
            req.setAttribute("token", token);
            req.setAttribute("params", params);
            req.getRequestDispatcher("AreaCheckServlet").forward(req, resp);
            //req.getRequestDispatcher("AreaCheckServlet").include(req, resp);
            //resp.getWriter().println("jjjj");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }

}
