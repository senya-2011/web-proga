package db.Observer;

import beans.PointsManagedBean;
import db.Factory.DataBaseFactoryManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import logic.LoggerInterface;
import qualifiers.DecoratorQualifier;

@ApplicationScoped
public class EventObserver {

    @Inject
    @DecoratorQualifier
    LoggerInterface loggerService;
    @Inject
    PointsManagedBean pointsManagedBean;
    @Inject
    DataBaseFactoryManager dataBaseFactoryManager;

    public void onDataBaseTypeChanged(@Observes DataBaseTypeChangeEvent event) {
        pointsManagedBean.setPoints(dataBaseFactoryManager.getDataBaseService().getPoints());
        loggerService.logInfo("DataBaseType changed from " + event.getOldValue() + " to " + event.getNewValue());
    }

    public void onFactoryTypeChanged(@Observes FactoryTypeChangeEvent event) {
        loggerService.logInfo("ORM changed to " + event.getNewFactory());
    }
}
