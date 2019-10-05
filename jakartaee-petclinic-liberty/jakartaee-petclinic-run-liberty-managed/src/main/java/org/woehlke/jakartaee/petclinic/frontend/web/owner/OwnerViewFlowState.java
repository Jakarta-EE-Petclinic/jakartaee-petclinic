package org.woehlke.jakartaee.petclinic.frontend.web.owner;

import java.io.Serializable;

public enum OwnerViewFlowState implements Serializable {
    LIST,
    NEW,
    EDIT,
    DELETE,
    LIST_SEARCH_RESULT,
    SHOW,
    NEW_PET,
    EDIT_PET,
    NEW_VISIT;
}
