package db;

import db.interfaces.DataBaseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import logic.LoggerInterface;
import logic.Point;
import qualifiers.DecoratorQualifier;

import java.io.Serializable;
import java.util.ArrayList;

@ApplicationScoped
@Named("MySqlDataBaseEclipse")
public class MySqlDataBaseEclipse implements DataBaseService, Serializable {
    @PersistenceContext(unitName = "MySQLPuEclipseLink")
    private EntityManager entityManager;

    @Inject
    @DecoratorQualifier
    LoggerInterface loggerService;

    @Override
    public void createPoint(Point point) {
        if (point != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(point);
            entityManager.getTransaction().commit();
            loggerService.logInfo(String.format("%s added to MySQL (EclipseLink)", point));
        }
    }

    @Override
    public ArrayList<Point> getPoints() {
        String sql = "SELECT * FROM Point";
        Query query = entityManager.createNativeQuery(sql, Point.class);
        loggerService.logInfo("Fetched points from MySQL (EclipseLink)");
        return new ArrayList<>(query.getResultList());
    }
}
