package org.woehlke.jakartaee.petclinic.frontend.web.common;

import java.io.Serializable;

public interface ViewHasSearch extends Serializable {

    String search();

    void performSearch();

    String getSearchterm();
    void setSearchterm(String searchterm);
}
