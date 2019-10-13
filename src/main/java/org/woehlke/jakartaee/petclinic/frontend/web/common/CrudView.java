package org.woehlke.jakartaee.petclinic.frontend.web.common;

import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface CrudView<T extends TwEntities> extends HasSearch, HasLanguage, Serializable {

	long serialVersionUID = -4976697275728754000L;

	String showNewForm();

	String saveNew();

	String cancelNew();

	String showEditForm();

	String saveEdited();

	String cancelEdited();

	String showDeleteForm();

	String performDelete();

	String cancelDelete();

	T getSelected();

	void setSelected(T selected);

	T getEntity();

	void setEntity(T entity);

	List<T> getList();

	void setList(List<T> list);
}
