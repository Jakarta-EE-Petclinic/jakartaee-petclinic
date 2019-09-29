package org.woehlke.javaee8.petclinic.oodm.dao.common;

import org.woehlke.javaee8.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T extends TwEntities> extends Serializable {

    List<T> getAll();
    T findById(long id);

    T addNew(T entity);
    T update(T entity);

    void delete(long id);

}
