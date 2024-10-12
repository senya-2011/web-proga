package logic.RequestManagers;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class RequestReader {
    JsonParser jsonParser = new JsonParser();
    public float[] readRequest(HttpServletRequest request){
        try{
            request.setCharacterEncoding("UTF-8");
            StringBuilder jsonBuffer = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonBuffer.append(line);
                }
            }
            return jsonParser.getFloats(jsonBuffer.toString());
        }catch (Exception e){
            return new float[]{0, 0, 0, 404};
        }
    }
}
