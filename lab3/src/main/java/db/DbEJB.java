package db;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import logic.Point;
import java.util.ArrayList;

@Stateless
public class DbEJB {

    @PersistenceContext(unitName = "dbPu")
    private EntityManager entityManager;

    @Transactional
    public void createPoint(Point point){
        if(point!=null){
            entityManager.persist(point);
        }
    }

    @Transactional
    public ArrayList<Point> getPoints() {
        TypedQuery<Point> query = entityManager.createQuery("SELECT point FROM Point point", Point.class);
        if(!query.getResultList().isEmpty()){
            return new ArrayList<>();
        }
        return (ArrayList<Point>) query.getResultList();
    }
}
