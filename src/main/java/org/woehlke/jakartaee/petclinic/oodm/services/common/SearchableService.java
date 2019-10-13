package org.woehlke.jakartaee.petclinic.oodm.services.common;

import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface SearchableService<T extends TwEntities> extends Serializable {

  long serialVersionUID = -1893303126489909752L;

  List<T> search(String searchterm);
}
