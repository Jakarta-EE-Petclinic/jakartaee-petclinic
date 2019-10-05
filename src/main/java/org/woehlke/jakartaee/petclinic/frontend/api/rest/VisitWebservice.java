package org.woehlke.jakartaee.petclinic.frontend.api.rest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.entities.model.VisitList;
import org.woehlke.jakartaee.petclinic.oodm.dao.VisitDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@Stateless
@Path("/visit")
public class VisitWebservice  implements Serializable {

    private static final long serialVersionUID = 7444366391126982311L;

    private static Logger log = LogManager.getLogger(VisitWebservice.class.getName());

    @EJB
    private VisitDao visitDao;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/list")
    public VisitList getList(){
        log.debug("getList");
        VisitList list = new VisitList(visitDao.getAll());
        return list;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    @Path("/list/json")
    public VisitList getListJson(){
        return this.getList();
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML})
    @Path("/list/xml")
    public VisitList getListXml(){
        return this.getList();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Visit getEntity(@PathParam("id") Long id){
        log.debug("getEntity");
        Visit visit = null;
        try {
            visit = visitDao.findById(id);
        } catch (Exception ex) {
            log.warn("getEntity: ",ex);
        }
        return visit;
    }

}
