package org.woehlke.javaee8.petclinic.oodm.services;

import org.woehlke.javaee8.petclinic.oodm.entities.Pet;
import org.woehlke.javaee8.petclinic.oodm.services.common.CrudService;

import java.io.Serializable;

public interface PetService extends CrudService<Pet>, Serializable {

}
