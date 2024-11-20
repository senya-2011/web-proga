package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@SessionScoped
@Named("loginBean")
public class LoginBean implements Serializable {
    private String password;

    public String login(){
        if ("admin".equals(password)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("password", password);
            return "admin.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
