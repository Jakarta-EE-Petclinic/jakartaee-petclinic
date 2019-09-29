package org.woehlke.javaee8.petclinic.oodm.services;

import org.woehlke.javaee8.petclinic.oodm.entities.Vet;
import org.woehlke.javaee8.petclinic.oodm.services.common.CrudService;
import org.woehlke.javaee8.petclinic.oodm.services.common.SearchableService;

import java.io.Serializable;

public interface VetService extends CrudService<Vet>, SearchableService<Vet>, Serializable {

}
