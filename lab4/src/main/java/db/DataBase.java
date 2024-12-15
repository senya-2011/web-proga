package db;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import logic.LoggerService;
import logic.Point;
import logic.User;

import java.util.ArrayList;

@Stateless
public class DataBase {

    @Inject
    LoggerService loggerService;
    @PersistenceContext(unitName = "dbPu")
    private EntityManager entityManager;

    @Transactional
    public void createPoint(Point point){
        if(point!=null){
            loggerService.logInfo("Добавили точку в бд: "+point);
            entityManager.persist(point);
        }
    }

    @Transactional
    public void createUser(User user){
        if(user!=null){
            loggerService.logInfo("Добавили юзера в бд: "+user);
            entityManager.persist(user);
        }
    }

    @Transactional
    public User findByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public ArrayList<Point> getPoints() {
        TypedQuery<Point> query = entityManager.createQuery("SELECT point FROM Point point", Point.class);
        loggerService.logInfo(query.getResultList().toString());
        if(query.getResultList().isEmpty()){
            return new ArrayList<>();
        }
        return (ArrayList<Point>) query.getResultList();
    }

    @Transactional
    public ArrayList<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
        loggerService.logInfo(query.getResultList().toString());
        if(query.getResultList().isEmpty()){
            return new ArrayList<>();
        }
        return (ArrayList<User>) query.getResultList();
    }
}
