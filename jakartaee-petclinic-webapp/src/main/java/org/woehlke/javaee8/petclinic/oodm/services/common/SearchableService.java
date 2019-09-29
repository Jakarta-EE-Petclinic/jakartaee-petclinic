package org.woehlke.javaee8.petclinic.oodm.services.common;

import org.woehlke.javaee8.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface SearchableService<T extends TwEntities> extends Serializable {

    List<T> search(String searchterm);
}
