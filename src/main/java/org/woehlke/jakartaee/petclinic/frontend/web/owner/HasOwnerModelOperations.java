package org.woehlke.jakartaee.petclinic.frontend.web.owner;

import org.woehlke.jakartaee.petclinic.frontend.web.common.HasViewModelOperations;

public interface HasOwnerModelOperations extends HasViewModelOperations {

	long serialVersionUID = 1490389883743112662L;

	void loadPetTypeList();
}
