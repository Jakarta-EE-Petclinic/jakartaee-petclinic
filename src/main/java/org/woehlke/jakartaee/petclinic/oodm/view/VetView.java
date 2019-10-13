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

	long serialVersionUID = -4141782100256382881L;

	Specialty findSpecialtyByName(String name);
}
