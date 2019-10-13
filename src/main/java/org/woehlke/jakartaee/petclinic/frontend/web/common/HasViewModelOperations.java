package org.woehlke.jakartaee.petclinic.frontend.web.common;

import java.io.Serializable;

public interface HasViewModelOperations extends Serializable {

  long serialVersionUID = 6750797521381818959L;

  void reloadEntityFromSelected();

  void loadList();

  void saveNewEntity();

  void saveEditedEntity();

  void deleteSelectedEntity();

  void newEntity();

  void init();

  void preDestroy();
}
