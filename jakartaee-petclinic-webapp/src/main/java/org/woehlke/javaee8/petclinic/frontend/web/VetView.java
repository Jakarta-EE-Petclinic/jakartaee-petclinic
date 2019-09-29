package org.woehlke.javaee8.petclinic.frontend.web;

import org.woehlke.javaee8.petclinic.frontend.web.common.CrudView;
import org.woehlke.javaee8.petclinic.oodm.entities.Specialty;
import org.woehlke.javaee8.petclinic.oodm.entities.Vet;

public interface VetView extends CrudView<Vet> {

    Specialty findSpecialtyByName(String name);
}
