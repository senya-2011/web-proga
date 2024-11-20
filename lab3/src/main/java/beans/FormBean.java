package beans;

import db.Factory.DataBaseFactoryManager;
import jakarta.annotation.PostConstruct;
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

    @Inject
    DataBaseFactoryManager dataBaseFactoryManager;

    @Inject
    PointsManagedBean pointsManagedBean;
    @Inject
    DynamicDataBaseBean dynamicDataBaseBean;
    @Inject
    AreaChecker areaChecker;

    private String x="1";
    private String y="1";
    private String r="1";
    private boolean status;
    private int trigger=1;

    private final Random random = new Random();

    @Transactional
    public String submit(){
        randTrigger();
        status = areaChecker.checkArea(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(r));
        Point point = new Point(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(r), status);
        point.setId(dynamicDataBaseBean.getId());
        dataBaseFactoryManager.getDataBaseService().createPoint(point);
        pointsManagedBean.setPoints(dataBaseFactoryManager.getDataBaseService().getPoints());
        return null;
    }

    @PostConstruct
    public void init(){
        pointsManagedBean.setPoints(dataBaseFactoryManager.getDataBaseService().getPoints());
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
    private void randTrigger(){
        setTrigger(random.nextInt(10000000));
    }
}
