package org.woehlke.jakartaee.petclinic.frontend.web.common;

import java.io.Serializable;

public interface ViewModelOperations extends Serializable {

    void reloadEntityFromSelected();
    void loadEntity();
    void loadList();
    void saveNewEntity();
    void saveEditedEntity();
    void deleteSelectedEntity();
    void newEntity();

    void init();
    void preDestroy();
}
