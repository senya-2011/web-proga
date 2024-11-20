package db.Factory;

import db.enums.DataBaseTypes;
import db.enums.FactoryTypes;
import db.interfaces.DataBaseFactory;
import db.interfaces.DataBaseService;
import db.qualifiers.FactoryType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@ApplicationScoped
@Named("DataBaseServiceFactory")
@FactoryType(FactoryTypes.Hibernate)
public class DataBaseServiceFactory implements DataBaseFactory,Serializable {
    @Inject
    @Named("PostgresDataBaseService")
    private DataBaseService postgresDataBaseService;

    @Inject
    @Named("MySqlDataBaseService")
    private DataBaseService mySqlDataBaseService;


    @Override
    public DataBaseService getDataBaseService(DataBaseTypes dbType) {
        switch (dbType) {
            case POSTGRES:
                return postgresDataBaseService;
            case MySQL:
                return mySqlDataBaseService;
            default:
                throw new IllegalArgumentException("No dataBase: " + dbType);
        }
    }
}
