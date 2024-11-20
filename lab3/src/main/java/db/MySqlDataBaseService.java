package db;

import db.interfaces.DataBaseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import logic.LoggerInterface;
import logic.Point;
import qualifiers.DecoratorQualifier;

import java.io.Serializable;
import java.util.ArrayList;

@ApplicationScoped
@Named("MySqlDataBaseService")
public class MySqlDataBaseService implements DataBaseService, Serializable {
    @PersistenceContext(unitName = "MySQLPu") //MySQLPu
    private EntityManager entityManager;

    @Inject
    @DecoratorQualifier
    LoggerInterface loggerService;

    @Override
    @Transactional
    public void createPoint(Point point) {
        if (point != null) {
            entityManager.persist(point);
            loggerService.logInfo(String.format("%s added to MySQL (Hibernate)", point));
        }
    }

    @Override
    public ArrayList<Point> getPoints() {
        String sql = "SELECT * FROM Point";
        Query query = entityManager.createNativeQuery(sql, Point.class);
        loggerService.logInfo("Fetched points from MySQL (Hibernate)");
        return new ArrayList<>(query.getResultList());
    }


}
