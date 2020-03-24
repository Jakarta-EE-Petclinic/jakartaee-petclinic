package org.woehlke.jakartaee.petclinic.frontend.api.rest;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.dao.VetDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;
import org.woehlke.jakartaee.petclinic.oodm.entities.model.VetList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 05.01.14
 * Time: 09:27
 * To change this template use File | Settings | File Templates.
 */
@Log4j2
@Stateless
@Path("/vet")
public class VetWebservice implements Serializable {

  private static final long serialVersionUID = 607664665910620584L;

  @EJB
  private VetDao vetDao;

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/list")
  public VetList getList() {
    log.debug("getList");
    VetList o = new VetList(vetDao.getAll());
    return o;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Path("/list/json")
  public VetList getListJson() {
    return this.getList();
  }

  @GET
  @Produces({MediaType.APPLICATION_XML})
  @Path("/list/xml")
  public VetList getListXml() {
    return this.getList();
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/{id}")
  public Vet getEntity(@PathParam("id") Long id) {
    log.debug("getEntity");
    Vet o = null;
    try {
      o = vetDao.findById(id);
    } catch (Exception ex) {
      log.warn("getEntity: ", ex);
    }
    return o;
  }
}
