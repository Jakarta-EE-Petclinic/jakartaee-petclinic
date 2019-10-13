package org.woehlke.jakartaee.petclinic.oodm.services;

import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;
import org.woehlke.jakartaee.petclinic.oodm.services.common.CrudService;

import java.io.Serializable;

public interface PetService extends CrudService<Pet>, Serializable {

	long serialVersionUID = 7113444329343577727L;
}
