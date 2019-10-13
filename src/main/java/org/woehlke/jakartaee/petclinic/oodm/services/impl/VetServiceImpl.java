package org.woehlke.jakartaee.petclinic.oodm.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.dao.SpecialtyDao;
import org.woehlke.jakartaee.petclinic.oodm.dao.VetDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;
import org.woehlke.jakartaee.petclinic.oodm.services.VetService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

@Stateless
public class VetServiceImpl implements VetService {

  private static final long serialVersionUID = 2698313227542867286L;

  private static Logger log = LogManager.getLogger(VetServiceImpl.class.getName());

  @EJB
  private VetDao vetDao;

  @EJB
  private SpecialtyDao specialtyDao;

  @Override
  public List<Vet> getAll() {
    return vetDao.getAll();
  }

  @Override
  public void delete(long id) {
    this.vetDao.delete(id);
  }

  @Override
  public Vet addNew(Vet vet) {
    vet.setUuid(UUID.randomUUID());
    log.debug("try to addNew: " + vet.toString());
    return this.vetDao.addNew(vet);
  }

  @Override
  public Vet findById(long id) {
    return this.vetDao.findById(id);
  }

  @Override
  public Vet update(Vet vet) {
    return this.vetDao.update(vet);
  }

  @Override
  public List<Vet> search(String searchterm) {
    return this.vetDao.search(searchterm);
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
