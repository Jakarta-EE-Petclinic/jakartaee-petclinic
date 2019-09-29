package org.woehlke.javaee8.petclinic.oodm.dao;

import org.woehlke.javaee8.petclinic.oodm.dao.common.CrudDao;
import org.woehlke.javaee8.petclinic.oodm.dao.common.Searchable;
import org.woehlke.javaee8.petclinic.oodm.entities.Specialty;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.01.14
 * Time: 12:02
 * To change this template use File | Settings | File Templates.
 */
public interface SpecialtyDao extends CrudDao<Specialty>, Searchable<Specialty> {

    Specialty findSpecialtyByName(String name);
}
