package org.woehlke.jakartaee.petclinic.oodm.view;

import org.woehlke.jakartaee.petclinic.frontend.web.common.CrudView;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasLanguage;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasSearch;
import org.woehlke.jakartaee.petclinic.frontend.web.common.HasViewModelOperations;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

import java.io.Serializable;

public interface PetTypeView extends CrudView<PetType>,
		HasLanguage,
		HasSearch,
		HasViewModelOperations,
		Serializable {

	long serialVersionUID = -7305835717012880655L;
}
