package org.woehlke.javaee8.petclinic.oodm.services;

import org.woehlke.javaee8.petclinic.oodm.entities.PetType;
import org.woehlke.javaee8.petclinic.oodm.services.common.CrudService;
import org.woehlke.javaee8.petclinic.oodm.services.common.SearchableService;

import java.io.Serializable;

public interface PetTypeService extends CrudService<PetType>, SearchableService<PetType>, Serializable {

    PetType findByName(String name);
}
