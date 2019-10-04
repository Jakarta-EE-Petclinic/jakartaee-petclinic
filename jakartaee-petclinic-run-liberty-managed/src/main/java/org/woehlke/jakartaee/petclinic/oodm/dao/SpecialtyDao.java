package org.woehlke.jakartaee.petclinic.oodm.dao;

import org.woehlke.jakartaee.petclinic.oodm.dao.common.CrudDao;
import org.woehlke.jakartaee.petclinic.oodm.dao.common.Searchable;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.01.14
 * Time: 12:02
 * To change this template use File | Settings | File Templates.
 */
public interface SpecialtyDao extends CrudDao<Specialty>, Searchable<Specialty> {

    long serialVersionUID = -3942585068863132460L;

    Specialty findSpecialtyByName(String name);
}
