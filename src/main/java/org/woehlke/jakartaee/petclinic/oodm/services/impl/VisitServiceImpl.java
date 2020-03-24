package org.woehlke.jakartaee.petclinic.oodm.services.impl;

import lombok.extern.log4j.Log4j2;
import org.woehlke.jakartaee.petclinic.oodm.dao.VisitDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;
import org.woehlke.jakartaee.petclinic.oodm.services.VisitService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import java.util.List;

@Log4j2
@Stateless
public class VisitServiceImpl implements VisitService {

  private static final long serialVersionUID = 4560958540651968289L;

  @EJB
  private VisitDao visitDao;

  @Override
  public List<Visit> getAll() {
    return this.visitDao.getAll();
  }

  @Override
  public Visit findById(long id) {
    return this.visitDao.findById(id);
  }

  @Override
  public Visit addNew(Visit visit) {
    log.debug("about to addNew: " + visit.toString());
    return this.visitDao.addNew(visit);
  }

  @Override
  public Visit update(Visit visit) {
    log.debug("about to update: " + visit.toString());
    return this.visitDao.update(visit);
  }

  @Override
  public void delete(long id) {
    log.debug("about to delete: " + id);
    this.visitDao.delete(id);
  }


  @PostConstruct
  public void postConstruct() {
    log.debug("postConstruct");
  }

  @PreDestroy
  public void preDestroy() {
    log.debug("preDestroy");
  }

  @PrePassivate
  public void prePassivate() {
    log.debug("prePassivate");
  }

  @PostActivate
  public void postActivate() {
    log.debug("postActivate");
  }
}
