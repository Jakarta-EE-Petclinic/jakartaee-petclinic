package org.woehlke.jakartaee.petclinic.oodm.view;

import org.woehlke.jakartaee.petclinic.frontend.web.common.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

import java.io.Serializable;

public interface PetTypeView extends CrudView<PetType>,
        HasLanguage,
        HasSearch,
        HasViewModelOperations,
        Serializable {
}
