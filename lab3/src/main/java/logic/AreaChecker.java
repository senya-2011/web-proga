package logic;

public class AreaChecker{
    public boolean checkArea(float x, float y, float r){
        return isHit(x, y, r); // && (x>=-4 && x<=4) && (y>=-3 && y<=3) && (r>=1 && r<=3)

    }

    private boolean isHit(float x, float y, float r){
        return (checkSquare(x,y,r)) || (checkTriangle(x,y,r)) || (checkCircle(x,y,r));
    }
    private boolean checkSquare(float x, float y, float r){
        return (r*x>=0 && r*y<=0 && Math.abs(x)<=r && Math.abs(y)<=r);
    }
    private boolean checkTriangle(float x, float y, float r){
        //точки треугольника
        float x1 = -0.5f;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        int x3 = 0;
        float y3 = 0.5f;

        //проверка суммой площадей
        float s = r*r*Math.abs(x1*(y2-y3)+x2*(y3-y1));//+x3*(y1-y2));
        float s1 = Math.abs(x*(r*y1-r*y2)+r*x1*(r*y2-y)+x2*(y-r*y1));
        float s2 = Math.abs(x*(r*y2-r*y3)+r*x2*(r*y3-y)+x3*(y-r*y2));
        float s3 = Math.abs(x*(r*y3-r*y1)+r*x3*(r*y1-y)+r*x1*(y-r*y3));

        return s==(s1+s2+s3);
    }
    private boolean checkCircle(float x, float y, float r){
        return (Math.sqrt(x*x + y*y)<=Math.abs(r)) && (r*x<0 && r*y<0);
    }
}