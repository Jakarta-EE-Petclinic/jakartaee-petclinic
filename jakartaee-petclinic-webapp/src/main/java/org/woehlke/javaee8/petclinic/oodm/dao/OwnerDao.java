package org.woehlke.javaee8.petclinic.oodm.dao;

import org.woehlke.javaee8.petclinic.oodm.dao.common.CrudDao;
import org.woehlke.javaee8.petclinic.oodm.dao.common.Searchable;
import org.woehlke.javaee8.petclinic.oodm.entities.Owner;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.01.14
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
public interface OwnerDao extends CrudDao<Owner>, Searchable<Owner> {

}
