package org.woehlke.jakartaee.petclinic.frontend.web.owner;

import org.woehlke.jakartaee.petclinic.frontend.web.common.HasViewModelOperations;

public interface HasOwnerModelOperations extends HasViewModelOperations {

    void loadPetTypeList();
}
