package org.woehlke.jakartaee.petclinic.oodm.entities.common;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.UUID;

public interface TwEntities<T extends TwEntities> extends Serializable, Comparable<T> {

    long serialVersionUID = -3378330831315654285L;

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
