package org.woehlke.jakartaee.petclinic.frontend.web.common;

import java.io.Serializable;

public enum CrudViewFlowState implements Serializable {
    LIST,
    NEW,
    EDIT,
    DELETE,
    LIST_SEARCH_RESULT
}
