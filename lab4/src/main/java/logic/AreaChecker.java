package logic;

import jakarta.ejb.Stateless;

@Stateless
public class AreaChecker{

    //square
    private final int squareXSide = 1;
    private final int squareYSide = -1;
    private final int squareX = 2;
    private final int squareY = 2;

    //triangle
    private final int triangleXSide = 1;
    private final int triangleYSide = 1;
    private final int triangleX = 2;
    private final int triangleY = 2;

    //circle
    private final int circleXSide = -1;
    private final int circleYSide = -1;
    private final int radius = 2;





    public boolean checkArea(float x, float y, float r){
        return isHit(x, y, r);

    }

    private boolean isHit(float x, float y, float r){
        return (checkSquare(x,y,r)) || (checkTriangle(x,y,r)) || (checkCircle(x,y,r));
    }
    private boolean checkSquare(float x, float y, float r){
        return (squareXSide*r*x>=0 && squareYSide*r*y>=0 && Math.abs(x)<=r * squareX/2 && Math.abs(y)<=r *squareY/2);
    }
    private boolean checkTriangle(float x, float y, float r){
        //точки треугольника
        float x1 = triangleX * triangleXSide*r/2;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        int x3 = 0;
        float y3 = triangleY * triangleYSide*r/2;

        float totalArea = Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) ) / 2.0f;
        float area1 = Math.abs(x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2)) / 2.0f;
        float area2 = Math.abs(x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y)) / 2.0f;
        float area3 = Math.abs(x1 * (y2 - y) + x2 * (y - y1)) / 2.0f;
        return (totalArea == area1 + area2 + area3);

    }
    private boolean checkCircle(float x, float y, float r){
        return (Math.sqrt(x*x + y*y)<=Math.abs(r* radius/2)) && (circleXSide*r*x>0 && circleYSide*r*y>0);
    }
}