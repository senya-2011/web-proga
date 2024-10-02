import com.fastcgi.FCGIInterface;

public class Main {

    public static void main(String[] args) {
        ResponseSender responseSender = new ResponseSender();
        FCGIInterface fcgiInterface = new FCGIInterface();

        while (fcgiInterface.FCGIaccept() >= 0) {
            responseSender.sendResponse();
        }
    }
}
