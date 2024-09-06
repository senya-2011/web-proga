import com.fastcgi.FCGIInterface;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.*;

public class Main {

    public static boolean checkData(float x, float y, float r){
        return ((x>-5 && x<3) && (y>-3 && y<3) && (r>=1 && r<=5));
    }
    public static boolean isHit(float x, float y, float r){
        if(checkSquare(x,y,r)){
            return true;
        }else if(checkTriangle(x,y,r)){
            return true;
        }else{
            return checkCircle(x,y,r);
        }
    }
    private static boolean checkSquare(float x, float y, float r){
        return (r*x>=0 && r*y<=0 && Math.abs(x)<=r && 2*Math.abs(y)<=r);
    }
    private static boolean checkTriangle(float x, float y, float r){
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
    private static boolean checkCircle(float x, float y, float r){
        if(Math.sqrt(x*x + y*y)>Math.abs(r/2)){
            return false;
        }
        return (r*x>0 && r*y>0);
    }

    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        FCGIInterface fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            try {
                String content = """
                {
                    "status": "%s",
                    "time": "%s"
                }
                """;
                float[] floats = readRequestBody();
                float x = floats[0];
                float y = floats[1];
                float r = floats[2];
                boolean status;
                if(checkData(x, y, r)){
                    status = isHit(x, y, r);
                }else{
                    status = false;
                }
                
                long endTime = System.currentTimeMillis();
                long executeTime = endTime - startTime;
                content = content.formatted(status, executeTime);

                String response = """
                        HTTP/2 200 OK
                        Content-Type: application/json
                        Content-Length: %d
                        
                        %s
                        
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

                System.out.println(response);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    
    private static float[] readRequestBody(){
        try{
            FCGIInterface.request.inStream.fill();
            var contentLength = FCGIInterface.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes =
                    FCGIInterface.request.inStream.read(buffer.array(), 0,
                            contentLength);
            var requestBodyRaw = new byte[readBytes];
            buffer.get(requestBodyRaw);
            buffer.clear();
            String requestString = new String(requestBodyRaw, StandardCharsets.UTF_8);
            String[] elements = requestString.split(":");
            float[] floats = new float[3];
            if(elements.length!=4){
                return new float[]{-20,-20,-20};
            }
            try{
                floats[0] = Float.parseFloat((elements[1].split(",")[0]));
                floats[1] = Float.parseFloat((elements[2].split(",")[0]));
                floats[2] = Float.parseFloat(elements[3].split("}")[0].replace("}", ""));
                return floats;
            }catch (Exception e){
                return new float[]{-20,-20,-20};
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
