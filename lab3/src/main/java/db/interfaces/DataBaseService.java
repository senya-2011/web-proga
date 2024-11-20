package db.interfaces;
import logic.Point;
import java.util.ArrayList;

public interface DataBaseService {
    void createPoint(Point point);
    ArrayList<Point> getPoints();
}
