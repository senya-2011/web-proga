package db.Factory;

import db.Observer.DataBaseTypeChangeEvent;
import db.Observer.FactoryTypeChangeEvent;
import db.enums.DataBaseTypes;
import db.enums.FactoryTypes;
import db.interfaces.DataBaseFactory;
import db.interfaces.DataBaseService;
import db.qualifiers.FactoryType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class DataBaseFactoryManager {
    private DataBaseFactory currentFactory;
    private DataBaseTypes dataBaseType = DataBaseTypes.MySQL;

    @Inject
    @FactoryType(FactoryTypes.Hibernate)
    private DataBaseFactory hibernateFactory;

    @Inject
    @FactoryType(FactoryTypes.Eclipselink)
    private DataBaseFactory eclipseLinkFactory;

    @Inject
    @FactoryType(FactoryTypes.JDBC)
    private DataBaseFactory jdbcLinkFactory;

    @Inject
    private Event<DataBaseTypeChangeEvent> event;

    @Inject
    private Event<FactoryTypeChangeEvent> factoryTypeChangeEventEvent;

    public DataBaseService getDataBaseService(){
        if(currentFactory==null){
            currentFactory = eclipseLinkFactory;
        }
        return currentFactory.getDataBaseService(dataBaseType);
    }

    public void setFactoryType(FactoryTypes factoryType){
        switch (factoryType) {
            case Hibernate:
                currentFactory = hibernateFactory;
                break;
            case Eclipselink:
                currentFactory = eclipseLinkFactory;
                break;
            case JDBC:
                currentFactory = jdbcLinkFactory;
                break;
            default:
                throw new IllegalArgumentException("No support: " + factoryType);
        }
        factoryTypeChangeEventEvent.fire(new FactoryTypeChangeEvent(factoryType));
    }
    public void setDataBaseType(DataBaseTypes dataBaseType){
        event.fire(new DataBaseTypeChangeEvent(this.dataBaseType, dataBaseType));
        this.dataBaseType = dataBaseType;
    }
}
