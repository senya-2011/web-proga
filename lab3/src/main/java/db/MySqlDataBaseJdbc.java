package db;

import db.interfaces.DataBaseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import logic.LoggerInterface;
import logic.Point;
import qualifiers.DecoratorQualifier;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

@ApplicationScoped
@Named("MySqlDataBaseJdbc")
public class MySqlDataBaseJdbc implements DataBaseService, Serializable {

    @Inject
    @DecoratorQualifier
    LoggerInterface loggerService;

    private Connection getConnection() {
        String url = "jdbc:mysql://mysqlDB:3306/lab3db";
        String user = "user";
        String password = "pass";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createPoint(Point point) {
        String sql = "INSERT INTO Point (id, x, y, r, status, session) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, point.getId());
            stmt.setFloat(2, point.getX());
            stmt.setFloat(3, point.getY());
            stmt.setFloat(4, point.getR());
            stmt.setBoolean(5, point.getStatus());
            stmt.setString(6, point.getSession());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loggerService.logInfo(String.format("%s added to MySQL (JDBC)", point));
    }

    @Override
    public ArrayList<Point> getPoints() {
        String sql = "SELECT id, x, y, r, status, session FROM Point";
        ArrayList<Point> points = new ArrayList<>();
        Connection connection = getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Point point = new Point();
                point.setId(rs.getLong("id"));
                point.setX(rs.getFloat("x"));
                point.setY(rs.getFloat("y"));
                point.setR(rs.getFloat("r"));
                point.setStatus(rs.getBoolean("status"));
                point.setSession(rs.getString("session"));

                points.add(point);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        loggerService.logInfo("Fetched points from MySQL (JDBC)");
        return points;
    }
}
