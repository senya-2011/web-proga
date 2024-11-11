package beans;

import db.DbEJB;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import logic.Point;

import java.io.Serializable;
import java.util.ArrayList;


@Named("pointsManagedBean")
@SessionScoped
public class PointsManagedBean implements Serializable {
    private ArrayList<Point> points;
    @EJB
    DbEJB dbEJB;

    @PostConstruct
    public void init(){
        this.points = dbEJB.getPoints();
    }

    @Transactional
    public ArrayList<Point> getPoints(){
        return this.points;
    }

    public void setPoints(ArrayList<Point> points){
        this.points = points;
    }
}
