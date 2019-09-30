package org.woehlke.jakartaee.petclinic.oodm.view;

import org.woehlke.jakartaee.petclinic.frontend.web.common.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;

import java.io.Serializable;


public interface VetView extends CrudView<Vet>,
        HasLanguage,
        HasSearch,
        HasViewModelOperations,
        Serializable {

    Specialty findSpecialtyByName(String name);

}
