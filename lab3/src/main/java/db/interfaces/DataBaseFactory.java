package db.interfaces;

import db.enums.DataBaseTypes;

public interface DataBaseFactory {
    DataBaseService getDataBaseService(DataBaseTypes dbType);
}
