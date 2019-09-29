package org.woehlke.jakartaee.petclinic.frontend.web.common;

import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface CrudView<T extends TwEntities> extends ViewHasSearch, ViewHasLanguage, Serializable {

    String showEntityList();
    String showSelectedEntity();

    String showNewForm();
    String saveNew();

    String showEditForm();
    String saveEdited();

    String deleteSelected();
    String cancel();

    T getSelected();
    void setSelected(T selected);

    T getEntity();
    void setEntity(T entity);

    void setList(List<T> list);
    List<T> getList();
}
