package db.Observer;

import db.enums.DataBaseTypes;

public class DataBaseTypeChangeEvent {
    private final DataBaseTypes oldValue;
    private final DataBaseTypes newValue;

    public DataBaseTypeChangeEvent(DataBaseTypes oldValue, DataBaseTypes newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public DataBaseTypes getOldValue() {
        return oldValue;
    }

    public DataBaseTypes getNewValue() {
        return newValue;
    }
}
