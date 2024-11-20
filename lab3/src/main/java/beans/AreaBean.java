package beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import logic.LoggerInterface;
import qualifiers.DecoratorQualifier;

import java.util.Random;

@Named("areaBean")
@ApplicationScoped
public class AreaBean {
    @Inject
    @DecoratorQualifier
    LoggerInterface loggerService;

    //для автоматического обновления страницы
    private int updateTrigger=1;

    //четверти
    private String circleQ ="1,-1";
    private String triangleQ="-1,1";
    private String squareQ="-1,-1";

    //square
    private int squareX_side=1;
    private int squareY_side=-1;
    private float squareX=2f;
    private float squareY=2f;

    //triangle
    private int triangleX_side=-1;
    private int triangleY_side=1;
    private float triangleX=1f;
    private float triangleY=1f;

    //circle
    private int circleX_side=-1;
    private int circleY_side=-1;
    private float circleRadius=2f;
    private final Random random = new Random();

    @PostConstruct
    public void init() {
        loggerService.logInfo("areaBean init");
    }

    public void setCircleQ(String circleQ) {
        updateTrigger();
        String[] coords = circleQ.split(",");
        circleX_side = Integer.parseInt(coords[0]);
        circleY_side = Integer.parseInt(coords[1]);
        this.circleQ = circleQ;
    }

    private void updateTrigger(){
        this.updateTrigger = random.nextInt(10000000);
    }

    public String getTriangleQ() {
        return triangleQ;
    }

    public void setTriangleQ(String triangleQ) {
        updateTrigger();
        String[] coords = triangleQ.split(",");
        triangleX_side = Integer.parseInt(coords[0]);
        triangleY_side = Integer.parseInt(coords[1]);
        this.triangleQ = triangleQ;
    }

    public String getSquareQ() {
        return squareQ;
    }

    public void setSquareQ(String squareQ) {
        updateTrigger();
        String[] coords = squareQ.split(",");
        squareX_side = Integer.parseInt(coords[0]);
        squareY_side = Integer.parseInt(coords[1]);
        this.squareQ = squareQ;
    }
    public String getCircleQ() {
        return circleQ;
    }

    public int getSquareX_side() {
        return squareX_side;
    }

    public void setSquareX_side(int squareX_side) {
        this.squareX_side = squareX_side;
    }

    public int getSquareY_side() {
        return squareY_side;
    }

    public void setSquareY_side(int squareY_side) {
        this.squareY_side = squareY_side;
    }

    public int getUpdateTrigger() {
        return updateTrigger;
    }

    public void setUpdateTrigger(int updateTrigger) {
        this.updateTrigger = updateTrigger;
    }

    public float getSquareX() {
        return squareX;
    }

    public void setSquareX(float squareX) {
        updateTrigger();
        this.squareX = squareX;
    }

    public float getSquareY() {
        return squareY;
    }

    public void setSquareY(float squareY) {
        updateTrigger();
        this.squareY = squareY;
    }

    public int getTriangleX_side() {
        return triangleX_side;
    }

    public void setTriangleX_side(int triangleX_side) {
        this.triangleX_side = triangleX_side;
    }

    public int getTriangleY_side() {
        return triangleY_side;
    }

    public void setTriangleY_side(int triangleY_side) {
        this.triangleY_side = triangleY_side;
    }

    public float getTriangleX() {
        return triangleX;
    }

    public void setTriangleX(float triangleX) {
        updateTrigger();
        this.triangleX = triangleX;
    }

    public float getTriangleY() {
        return triangleY;
    }

    public void setTriangleY(float triangleY) {
        updateTrigger();
        this.triangleY = triangleY;
    }

    public int getCircleX_side() {
        return circleX_side;
    }

    public void setCircleX_side(int circleX_side) {
        this.circleX_side = circleX_side;
    }

    public int getCircleY_side() {
        return circleY_side;
    }

    public void setCircleY_side(int circleY_side) {
        this.circleY_side = circleY_side;
    }

    public float getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(float circleRadius) {
        updateTrigger();
        this.circleRadius = circleRadius;
    }
}
