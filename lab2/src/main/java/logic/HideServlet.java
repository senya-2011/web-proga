package logic;

import javax.servlet.http.HttpServletRequest;

public class HideServlet {
    public boolean checkReferrer(HttpServletRequest request) {
        try {
            String tokenFromSession = (String) request.getSession().getAttribute("token");
            String tokenFromRequest = (String) request.getAttribute("token");

            return (tokenFromRequest == null || !tokenFromRequest.equals(tokenFromSession));
        }catch (Exception e){
            return true;
        }
    }
}

