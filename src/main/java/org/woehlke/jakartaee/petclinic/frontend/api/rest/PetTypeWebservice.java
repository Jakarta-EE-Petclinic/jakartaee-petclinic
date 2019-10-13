package org.woehlke.jakartaee.petclinic.frontend.api.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.dao.PetTypeDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;
import org.woehlke.jakartaee.petclinic.oodm.entities.model.PetTypeList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@Stateless
@Path("/petType")
public class PetTypeWebservice implements Serializable {

	private static final long serialVersionUID = -105453087511255998L;

	private static Logger log = LogManager.getLogger(PetTypeWebservice.class.getName());

	@EJB
	private PetTypeDao petTypeDao;

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/list")
	public PetTypeList getList() {
		log.debug("getList");
		PetTypeList list = new PetTypeList(petTypeDao.getAll());
		return list;
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/list/json")
	public PetTypeList getListJson() {
		return this.getList();
	}

	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/list/xml")
	public PetTypeList getListXml() {
		return this.getList();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{id}")
	public PetType getEntity(@PathParam("id") Long id) {
		log.debug("getEntity");
		PetType o = null;
		try {
			o = petTypeDao.findById(id);
		} catch (Exception ex) {
			log.warn("getEntity: ", ex);
		}
		return o;
	}
}
