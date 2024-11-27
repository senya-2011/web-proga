package beans;

import db.Factory.DataBaseFactoryManager;
import db.enums.DataBaseTypes;
import db.enums.FactoryTypes;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("dynamicDataBaseBean")
@ApplicationScoped
public class DynamicDataBaseBean {
    @Inject
    DataBaseFactoryManager dataBaseFactoryManager;

    private Long id=0l;

    private void incId(){
        id++;
    }
    public Long getId(){
        incId();
        return id;
    }
    public void switchFactoryToJdbc(){dataBaseFactoryManager.setFactoryType(FactoryTypes.JDBC);}
    public void switchToMySQL() {
        dataBaseFactoryManager.setDataBaseType(DataBaseTypes.MySQL);
    }
    public void switchToPostgres() {
        dataBaseFactoryManager.setDataBaseType(DataBaseTypes.POSTGRES);
    }
    public void switchFactoryToHibernate() {
        dataBaseFactoryManager.setFactoryType(FactoryTypes.Hibernate);
    }
    public void switchFactoryToEclipseLink() {
        dataBaseFactoryManager.setFactoryType(FactoryTypes.Eclipselink);
    }
}
