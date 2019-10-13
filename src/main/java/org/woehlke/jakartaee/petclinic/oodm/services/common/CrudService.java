package org.woehlke.jakartaee.petclinic.oodm.services.common;

import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T extends TwEntities> extends Serializable {

  long serialVersionUID = 8240918516324226703L;

  List<T> getAll();

  T findById(long id);

  T addNew(T entity);

  T update(T entity);

  void delete(long id);

}
