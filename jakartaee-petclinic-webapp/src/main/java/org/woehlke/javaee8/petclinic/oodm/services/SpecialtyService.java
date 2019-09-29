package org.woehlke.javaee8.petclinic.oodm.services;

import org.woehlke.javaee8.petclinic.oodm.entities.Specialty;
import org.woehlke.javaee8.petclinic.oodm.services.common.CrudService;
import org.woehlke.javaee8.petclinic.oodm.services.common.SearchableService;

import java.io.Serializable;

public interface SpecialtyService extends CrudService<Specialty>, SearchableService<Specialty>, Serializable {

    Specialty findSpecialtyByName(String name);

}
