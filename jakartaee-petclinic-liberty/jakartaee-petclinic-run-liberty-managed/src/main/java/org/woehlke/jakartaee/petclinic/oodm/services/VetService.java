package org.woehlke.jakartaee.petclinic.oodm.services;

import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;
import org.woehlke.jakartaee.petclinic.oodm.services.common.CrudService;
import org.woehlke.jakartaee.petclinic.oodm.services.common.SearchableService;

import java.io.Serializable;

public interface VetService extends CrudService<Vet>, SearchableService<Vet>, Serializable {
   long serialVersionUID = 6211608636423556157L;
}
