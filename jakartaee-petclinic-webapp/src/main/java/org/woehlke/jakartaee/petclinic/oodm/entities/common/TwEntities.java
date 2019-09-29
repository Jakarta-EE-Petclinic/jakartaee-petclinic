package org.woehlke.jakartaee.petclinic.oodm.entities.common;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.UUID;

public interface TwEntities<T extends TwEntities> extends Serializable, Comparable<T> {

    Long getId();

    UUID getUuid();

    @Transient
    String getTableName();

    @Transient
    String[] getColumnNames();

    @Transient
    String getPrimaryKey();

    @Transient
    String getPrimaryKeyWithId();
}
