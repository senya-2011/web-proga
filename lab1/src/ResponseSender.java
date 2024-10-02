import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ResponseSender {
    private Checker checker = new Checker();
    private JsonParser jsonParser = new JsonParser();


    public void sendResponse(){
        try {
            long startTime = System.nanoTime();
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
            boolean status = checker.getStatus(x,y,r);

            long endTime = System.nanoTime();
            long executeTime = (endTime - startTime);
            content = content.formatted(status, String.format("%.3f",(double) executeTime/1000000));

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
    private float[] readRequestBody(){
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
            if(!FCGIInterface.request.params.getProperty("REQUEST_METHOD").equals("POST")){
                return new float[]{-20,-20,-20};
            }
            return jsonParser.getFloats(requestString);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
