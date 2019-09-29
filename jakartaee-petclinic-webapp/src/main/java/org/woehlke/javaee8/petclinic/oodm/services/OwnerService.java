package org.woehlke.javaee8.petclinic.oodm.services;

import org.woehlke.javaee8.petclinic.oodm.entities.Owner;
import org.woehlke.javaee8.petclinic.oodm.entities.Visit;
import org.woehlke.javaee8.petclinic.oodm.services.common.CrudService;
import org.woehlke.javaee8.petclinic.oodm.services.common.SearchableService;

/**
 * Created by tw on 10.03.14.
 */
public interface OwnerService extends CrudService<Owner>, SearchableService<Owner> {

    Visit addNewVisit(Visit visit);
}
