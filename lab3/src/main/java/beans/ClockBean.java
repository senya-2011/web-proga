package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.text.SimpleDateFormat;
import java.util.Date;

@Named("clockBean")
@RequestScoped
public class ClockBean {

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(new Date());
    }
}
