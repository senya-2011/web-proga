package rest;

import db.DataBase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import logic.AreaChecker;
import logic.LoggerService;
import logic.Point;
import logic.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("/points")
public class PointsManager {

    @Inject
    LoggerService loggerService;
    @Inject
    DataBase dataBase;

    @Inject
    AreaChecker areaChecker;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Point> receivePoint(Map<String, Object> formData) {
        Object rValue = formData.get("r");
        Object xValue = formData.get("x");
        Object yValue = formData.get("y");
        float r = (rValue instanceof String) ? Float.parseFloat((String) rValue) : (rValue instanceof Number) ? ((Number) rValue).floatValue() : 0.0f;
        float x = (xValue instanceof String) ? Float.parseFloat((String) xValue) : (xValue instanceof Number) ? ((Number) xValue).floatValue() : 0.0f;
        float y = (yValue instanceof String) ? Float.parseFloat((String) yValue) : (yValue instanceof Number) ? ((Number) yValue).floatValue() : 0.0f;
        String login = (String) formData.get("login");
        User user = dataBase.findByLogin(login);
        Point point = new Point(x,y,r,areaChecker.checkArea(x, y, r), user);
        loggerService.logInfo("Получили точку: "+ point);
        dataBase.createPoint(point);
        return getReversePoints(point.getUser().getLogin());
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Point> getPoints(@QueryParam("login") String login) {
        loggerService.logInfo("Пришел запрос на точки");
        return getReversePoints(login);
    }

    private ArrayList<Point> getReversePoints(String login){
        List<Point> points = dataBase.getPoints();

        ArrayList<Point> reversedPoints = points.stream()
                .filter(point -> point.getUser().getLogin().equals(login)).collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(reversedPoints);
        loggerService.logInfo("Точки для клиента: "+reversedPoints);

        return reversedPoints;
    }
}
