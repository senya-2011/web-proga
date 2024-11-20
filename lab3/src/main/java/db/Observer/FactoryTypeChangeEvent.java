package db.Observer;

import db.enums.FactoryTypes;

public class FactoryTypeChangeEvent {
    private final FactoryTypes newFactory;

    public FactoryTypeChangeEvent(FactoryTypes newFactory) {
        this.newFactory = newFactory;
    }

    public FactoryTypes getNewFactory() {
        return newFactory;
    }
}