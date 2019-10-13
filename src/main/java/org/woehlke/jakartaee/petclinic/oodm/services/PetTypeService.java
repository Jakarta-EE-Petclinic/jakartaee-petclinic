package org.woehlke.jakartaee.petclinic.oodm.services;

import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;
import org.woehlke.jakartaee.petclinic.oodm.services.common.CrudService;
import org.woehlke.jakartaee.petclinic.oodm.services.common.SearchableService;

import java.io.Serializable;

public interface PetTypeService extends CrudService<PetType>, SearchableService<PetType>, Serializable {

  long serialVersionUID = 6637453269836393L;

  PetType findByName(String name);
}
