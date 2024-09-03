import com.fastcgi.FCGIInterface;
import com.fastcgi.FCGIMessage;

public class Main {

    public static boolean checkData(float x, float y, int r){
        return ((x>-5 && x<3) && (y>-3 && y<3) && (r>=1 && r<=5));
    }
    public static boolean isHit(float x, float y, int r){
        if(checkSquare(x,y,r)){
            return true;
        }else if(checkTriangle(x,y,r)){
            return true;
        }else{
            return checkCircle(x,y,r);
        }
    }
    private static boolean checkSquare(float x, float y, int r){
        return (r*x>=0 && r*y<=0 && Math.abs(x)<=r && 2*Math.abs(y)<=r);
    }
    private static boolean checkTriangle(float x, float y, int r){
        //точки треугольника
        int x1 = -1;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        int x3 = 0;
        int y3 = -1;

        //проверка суммой площадей
        float s = r*r*Math.abs(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2));
        float s1 = Math.abs(x*(r*y1-r*y2)+r*x1*(r*y2-y)+x2*(y-r*y1));
        float s2 = Math.abs(x*(r*y2-r*y3)+r*x2*(r*y3-y)+x3*(y-r*y2));
        float s3 = Math.abs(x*(r*y3-r*y1)+r*x3*(r*y1-y)+r*x1*(y-r*y3));

        return s==(s1+s2+s3);
    }
    private static boolean checkCircle(float x, float y, int r){
        if(Math.sqrt(x*x + y*y)>Math.abs(r/2)){
            return false;
        }
        return (r*x>0 && r*y>0);
    }

    public static void main(String[] args) {
//        FCGIMessage fcgiMessage = new FCGIMessage();
//        FCGIInterface fcgiinterface = new FCGIInterface();
//        int count = 0;
//        while(fcgiinterface.FCGIaccept() >= 0){
//            count++;
//            System.out.println("Hello World! №"+count);
//        }
        String message = "Данные не валидны!";
        try{
            String path = System.getenv("WEB_TEST");

            float x = Float.parseFloat(path.split(":")[0]);
            float y = Float.parseFloat(path.split(":")[1]);
            int r = Integer.parseInt(path.split(":")[2]);
            if(checkData(x,y,r)){
                System.out.println(isHit(x,y,r));
            }else{
                System.out.println(message);
            }
        }catch (Exception e){
            System.out.println(message);
       }
    }
}
