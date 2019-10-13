package org.woehlke.jakartaee.petclinic.oodm.dao.common;

import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T extends TwEntities> extends Serializable {

	long serialVersionUID = 5140497751059102450L;

	/**
	 * @see ./META-INF/persistence.xml
	 */
	String PERSISTENCE_UNIT_NAME = "jakartaeePetclinic";

	List<T> getAll();

	T findById(long id);

	T addNew(T entity);

	T update(T entity);

	void delete(long id);

}
