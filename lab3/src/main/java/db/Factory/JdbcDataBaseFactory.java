package db.Factory;

import db.enums.DataBaseTypes;
import db.enums.FactoryTypes;
import db.interfaces.DataBaseFactory;
import db.interfaces.DataBaseService;
import db.qualifiers.FactoryType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@ApplicationScoped
@FactoryType(FactoryTypes.JDBC)
public class JdbcDataBaseFactory implements DataBaseFactory{

    @Inject
    @Named("PostgresDataBaseJdbc")
    private DataBaseService postgresDataBaseService;

    @Inject
    @Named("MySqlDataBaseJdbc")
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
