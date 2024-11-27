package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import logic.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;


@Named("pointsManagedBean")
@SessionScoped
public class PointsManagedBean implements Serializable {

    @Inject
    HttpSession httpSession;
    private ArrayList<Point> points=null;

    @Transactional
    public ArrayList<Point> getPoints(){
        this.points = this.points.stream()
                .filter(point -> point.getSession().equals(httpSession.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        return this.points;
    }

    public void setPoints(ArrayList<Point> points){
        Collections.reverse(points);
        this.points = points;
    }
}
