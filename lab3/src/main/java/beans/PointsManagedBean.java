package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import logic.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


@Named("pointsManagedBean")
@SessionScoped
public class PointsManagedBean implements Serializable {
    private ArrayList<Point> points;

    @Transactional
    public ArrayList<Point> getPoints(){
        return this.points;
    }

    public void setPoints(ArrayList<Point> points){
        Collections.reverse(points);
        this.points = points;
    }
}
