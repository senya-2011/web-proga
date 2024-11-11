package beans;

import db.DbEJB;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import logic.AreaChecker;
import logic.Point;

import java.io.Serializable;
import java.util.Random;

@Named("formBean")
@SessionScoped
public class FormBean implements Serializable {
    @EJB
    DbEJB dbEJB;
    @Inject
    PointsManagedBean pointsManagedBean;
    private String x="1";
    private String y="1";
    private String r="1";
    private boolean status;
    private int trigger=1;
    private final AreaChecker areaChecker = new AreaChecker();

    @Transactional
    public String submit(){
        Random random = new Random();
        this.trigger = random.nextInt(10000000);
        status = areaChecker.checkArea(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(r));
        Point point = new Point(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(r), status);
        pointsManagedBean.getPoints().add(0, point);
        dbEJB.createPoint(point);
        return null;
    }

    public int getTrigger(){return trigger;}
    public void setTrigger(int trigger){this.trigger=trigger;}
    public boolean getStatus(){return status;}
    public void setStatus(boolean status){this.status=status;}
    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
    public void handleEvent(){}
}
