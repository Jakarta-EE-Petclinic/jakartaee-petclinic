package org.woehlke.jakartaee.petclinic.oodm.services;

import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;
import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;
import org.woehlke.jakartaee.petclinic.oodm.services.common.CrudService;
import org.woehlke.jakartaee.petclinic.oodm.services.common.SearchableService;

/**
 * Created by tw on 10.03.14.
 */
public interface OwnerService extends CrudService<Owner>, SearchableService<Owner> {

    Visit addNewVisit(Visit visit);
}
