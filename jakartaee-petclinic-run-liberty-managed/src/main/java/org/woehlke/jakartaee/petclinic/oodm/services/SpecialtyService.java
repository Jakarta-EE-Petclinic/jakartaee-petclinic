package org.woehlke.jakartaee.petclinic.oodm.services;

import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.services.common.CrudService;
import org.woehlke.jakartaee.petclinic.oodm.services.common.SearchableService;

import java.io.Serializable;

public interface SpecialtyService extends CrudService<Specialty>, SearchableService<Specialty>, Serializable {

    long serialVersionUID = -5259594533899166058L;

    Specialty findSpecialtyByName(String name);

}
