package org.woehlke.javaee8.petclinic.oodm.dao.common;

import org.woehlke.javaee8.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface Searchable<T extends TwEntities> extends Serializable {

    List<T> search(String searchterm);
    void resetSearchIndex();
}
