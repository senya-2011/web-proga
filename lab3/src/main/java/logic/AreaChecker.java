package logic;

import beans.AreaBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AreaChecker{
    @Inject
    @Named("areaBean")
    AreaBean areaBean;
    public boolean checkArea(float x, float y, float r){
        return isHit(x, y, r); // && (x>=-4 && x<=4) && (y>=-3 && y<=3) && (r>=1 && r<=3)

    }

    private boolean isHit(float x, float y, float r){
        return (checkSquare(x,y,r)) || (checkTriangle(x,y,r)) || (checkCircle(x,y,r));
    }
    private boolean checkSquare(float x, float y, float r){
        return (areaBean.getSquareX_side()*r*x>=0 && areaBean.getSquareY_side()*r*y>=0 && Math.abs(x)<=r * areaBean.getSquareX()/2 && Math.abs(y)<=r *areaBean.getSquareY()/2);
    }
    private boolean checkTriangle(float x, float y, float r){
        //точки треугольника
        float x1 = areaBean.getTriangleX() * areaBean.getTriangleX_side()*r/2;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        int x3 = 0;
        float y3 = areaBean.getTriangleY() * areaBean.getTriangleY_side()*r/2;

        float totalArea = Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) ) / 2.0f;
        float area1 = Math.abs(x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2)) / 2.0f;
        float area2 = Math.abs(x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y)) / 2.0f;
        float area3 = Math.abs(x1 * (y2 - y) + x2 * (y - y1)) / 2.0f;
        return (totalArea == area1 + area2 + area3);

    }
    private boolean checkCircle(float x, float y, float r){
        return (Math.sqrt(x*x + y*y)<=Math.abs(r* areaBean.getCircleRadius()/2)) && (areaBean.getCircleX_side()*r*x>0 && areaBean.getCircleY_side()*r*y>0);
    }
}