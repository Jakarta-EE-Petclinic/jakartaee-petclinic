package org.woehlke.jakartaee.petclinic.oodm.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.dao.PetDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;
import org.woehlke.jakartaee.petclinic.oodm.services.PetService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PetServiceImpl implements PetService {

  private static final long serialVersionUID = -2093524918552358722L;

  private static Logger log = LogManager.getLogger(PetServiceImpl.class.getName());

  @EJB
  private PetDao petDao;

  @Override
  public Pet addNew(Pet pet) {
    log.debug("about to addNew: " + pet.toString());
    return this.petDao.addNew(pet);
  }

  @Override
  public List<Pet> getAll() {
    return this.petDao.getAll();
  }

  @Override
  public Pet findById(long petId) {
    return this.petDao.findById(petId);
  }

  @Override
  public Pet update(Pet pet) {
    log.debug("about to update: " + pet.toString());
    return this.petDao.update(pet);
  }

  @Override
  public void delete(long id) {
    this.petDao.delete(id);
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
