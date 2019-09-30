package org.woehlke.jakartaee.petclinic.oodm.dao.common;

import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface Searchable<T extends TwEntities> extends Serializable {

    long serialVersionUID = -1799267609856447186L;

    List<T> search(String searchterm);
    void resetSearchIndex();
}
