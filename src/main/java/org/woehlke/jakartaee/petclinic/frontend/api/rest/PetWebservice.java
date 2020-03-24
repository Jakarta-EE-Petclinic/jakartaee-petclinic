package org.woehlke.jakartaee.petclinic.frontend.api.rest;


import lombok.extern.log4j.Log4j2;
import org.woehlke.jakartaee.petclinic.oodm.dao.PetDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;
import org.woehlke.jakartaee.petclinic.oodm.entities.model.PetList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;


@Log4j2
@Stateless
@Path("/pet")
public class PetWebservice implements Serializable {

  private static final long serialVersionUID = 6505290301528514574L;

  @EJB
  private PetDao petDao;

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/list")
  public PetList getList() {
    log.debug("getList");
    PetList list = new PetList(petDao.getAll());
    return list;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Path("/list/json")
  public PetList getListJson() {
    return this.getList();
  }

  @GET
  @Produces({MediaType.APPLICATION_XML})
  @Path("/list/xml")
  public PetList getListXml() {
    return this.getList();
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/{id}")
  public Pet getEntity(@PathParam("id") Long id) {
    log.debug("getEntity");
    Pet o = null;
    try {
      o = petDao.findById(id);
    } catch (Exception ex) {
      log.warn("getEntity: ", ex);
    }
    return o;
  }
}
