package org.woehlke.jakartaee.petclinic.frontend.api.rest;


import lombok.extern.log4j.Log4j2;
import org.woehlke.jakartaee.petclinic.oodm.dao.SpecialtyDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.entities.model.SpecialtyList;

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
@Path("/specialty")
public class SpecialtyWebservice implements Serializable {

  private static final long serialVersionUID = 607664665910620584L;

  @EJB
  private SpecialtyDao specialtyDao;

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/list")
  public SpecialtyList getList() {
    log.debug("getList");
    SpecialtyList list = new SpecialtyList(specialtyDao.getAll());
    return list;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Path("/list/json")
  public SpecialtyList getListJson() {
    return this.getList();
  }

  @GET
  @Produces({MediaType.APPLICATION_XML})
  @Path("/list/xml")
  public SpecialtyList getListXml() {
    return this.getList();
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/{id}")
  public Specialty getEntity(@PathParam("id") Long id) {
    log.debug("getEntity");
    Specialty o = null;
    try {
      o = specialtyDao.findById(id);
    } catch (Exception ex) {
      log.warn("getEntity: ", ex);
    }
    return o;
  }

}
