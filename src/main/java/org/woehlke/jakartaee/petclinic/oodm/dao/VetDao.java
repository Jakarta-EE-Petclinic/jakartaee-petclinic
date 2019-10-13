package org.woehlke.jakartaee.petclinic.oodm.dao;

import org.woehlke.jakartaee.petclinic.oodm.dao.common.CrudDao;
import org.woehlke.jakartaee.petclinic.oodm.dao.common.Searchable;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.01.14
 * Time: 08:37
 * To change this template use File | Settings | File Templates.
 */
public interface VetDao extends CrudDao<Vet>, Searchable<Vet> {

  long serialVersionUID = -8002507178196926932L;
}
