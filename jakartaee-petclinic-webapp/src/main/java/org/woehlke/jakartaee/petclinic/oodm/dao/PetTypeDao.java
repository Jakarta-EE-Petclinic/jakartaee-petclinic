package org.woehlke.jakartaee.petclinic.oodm.dao;

import org.woehlke.jakartaee.petclinic.oodm.dao.common.CrudDao;
import org.woehlke.jakartaee.petclinic.oodm.dao.common.Searchable;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.01.14
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public interface PetTypeDao extends CrudDao<PetType>, Searchable<PetType> {

    PetType findByName(String name);
}
