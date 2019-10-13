package org.woehlke.jakartaee.petclinic.oodm.view;

import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasLanguage;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasSearch;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasViewModelOperations;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;

import java.io.Serializable;

public interface SpecialtyView extends CrudView<Specialty>,
		HasSearch,
		HasLanguage,
		HasViewModelOperations,
		Serializable {

	long serialVersionUID = -3557696335568559475L;
}
