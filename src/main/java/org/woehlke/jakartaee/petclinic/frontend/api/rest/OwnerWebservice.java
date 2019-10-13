package org.woehlke.jakartaee.petclinic.frontend.api.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.entities.model.OwnerList;
import org.woehlke.jakartaee.petclinic.oodm.dao.OwnerDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@Stateless
@Path("/owner")
public class OwnerWebservice implements Serializable {

	private static final long serialVersionUID = 532726561254887897L;

	private static Logger log = LogManager.getLogger(OwnerWebservice.class.getName());

	@EJB
	private OwnerDao ownerDao;

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/list")
	public OwnerList getList() {
		log.debug("getList");
		OwnerList list = new OwnerList(ownerDao.getAll());
		return list;
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/list/json")
	public OwnerList getListJson() {
		return this.getList();
	}

	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/list/xml")
	public OwnerList getListXml() {
		return this.getList();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{id}")
	public Owner getEntity(@PathParam("id") Long id) {
		log.debug("getEntity");
		Owner o = null;
		try {
			o = ownerDao.findById(id);
		} catch (Exception ex) {
			log.warn("getEntity: ", ex);
		}
		return o;
	}
}
